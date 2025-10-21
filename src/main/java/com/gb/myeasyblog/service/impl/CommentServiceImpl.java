package com.gb.myeasyblog.service.impl;

import com.gb.myeasyblog.dto.CommentAddReqDTO;
import com.gb.myeasyblog.entity.Comment;
import com.gb.myeasyblog.mapper.CommentMapper;
import com.gb.myeasyblog.service.CommentService;
import com.gb.myeasyblog.util.HttpStatusConstants;
import com.gb.myeasyblog.util.Result;
import com.gb.myeasyblog.util.UserContext;
import com.gb.myeasyblog.vo.CommentGetRespVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    /**
     * 添加评论
     *
     * @param commentAddReqDTO 评论添加请求数据传输对象，包含评论内容、关联的文章或资源ID等信息
     * @return Result 返回操作结果，成功时返回"添加成功"，失败时返回相应的错误信息和状态码
     */
    @Override
    public Result add(CommentAddReqDTO commentAddReqDTO) {
        // 参数校验
        if (commentAddReqDTO == null) {
            return Result.error(HttpStatusConstants.BAD_REQUEST, "请求参数不能为空");
        }

        // 获取当前用户ID
        Long userId = UserContext.getUserId();
        if (userId == null) {
            return Result.error(HttpStatusConstants.UNAUTHORIZED, "用户未登录");
        }

        // 构造评论实体对象并设置基础属性
        Comment comment = new Comment();
        try {
            BeanUtils.copyProperties(commentAddReqDTO, comment);
        } catch (Exception e) {
            return Result.error(HttpStatusConstants.INTERNAL_ERROR, "参数转换失败");
        }

        // 处理父评论ID：如果未设置parentId，则保持null表示根评论
        if (commentAddReqDTO.getParentId() != null && commentAddReqDTO.getParentId() > 0) {
            comment.setParentId(commentAddReqDTO.getParentId());
        } else {
            comment.setParentId(null); // 明确设置为null表示根评论
        }

        comment.setUserId(userId);
        comment.setCreatedAt(LocalDateTime.now());

        // 执行数据库插入操作
        try {
            int count = commentMapper.insert(comment);
            if (count != 1) {
                return Result.error(HttpStatusConstants.INTERNAL_ERROR, "服务器内部错误");
            }
        } catch (Exception e) {
            return Result.error(HttpStatusConstants.INTERNAL_ERROR, "数据库操作失败");
        }

        return Result.success("添加成功");
    }



    /**
     * 获取所有评论信息
     *
     * @return Result<List<Comment>> 包含评论列表的统一返回结果，如果查询失败则返回空列表
     */
    @Override
    public Result<List<Comment>> getAllComment() {
        // 查询所有评论数据
        List<Comment> list = commentMapper.selectAllComment();
        // 如果查询结果为null，则初始化为空列表
        if (list == null) {
            list = new ArrayList<>();
        }
        // 返回成功的统一结果包装
        return Result.success(list);
    }
    
    
/**
 * 根据文章ID获取评论树形结构
 *
 * @param postId 文章ID
 * @return Result<List<CommentGetRespVO>> 树形结构的评论列表
 */
@Override
public Result<List<CommentGetRespVO>> getCommentsByPostId(Long postId) {
    // 参数校验
    if (postId == null || postId <= 0) {
        return Result.error(HttpStatusConstants.BAD_REQUEST, "文章ID不能为空");
    }
    
    try {
        // 从Mapper获取扁平的评论列表
        List<Comment> flatComments = commentMapper.selectByPostId(postId);
        
        // 转换为树形结构
        List<CommentGetRespVO> treeComments = buildCommentTree(flatComments);
        
        return Result.success(treeComments);
    } catch (Exception e) {
        return Result.error(HttpStatusConstants.INTERNAL_ERROR, "查询评论失败");
    }
}

/**
 * 将扁平的评论列表转换为树形结构
 *
 * @param comments 扁平的评论列表
 * @return 树形结构的评论列表
 */
private List<CommentGetRespVO> buildCommentTree(List<Comment> comments) {
    if (comments == null || comments.isEmpty()) {
        return new ArrayList<>();
    }
    
    // 创建CommentGetRespVO列表
    List<CommentGetRespVO> CommentGetRespVOs = comments.stream()
            .map(this::convertToVO)
            .collect(Collectors.toList());
    
    // 构建Map方便查找
    Map<Long, CommentGetRespVO> commentMap = new HashMap<>();
    for (CommentGetRespVO CommentGetRespVO : CommentGetRespVOs) {
        commentMap.put(CommentGetRespVO.getId(), CommentGetRespVO);
    }
    
    // 构建树形结构
    List<CommentGetRespVO> rootComments = new ArrayList<>();
    for (CommentGetRespVO CommentGetRespVO : CommentGetRespVOs) {
        Long parentId = CommentGetRespVO.getParentId();
        if (parentId == null) {
            // 根评论
            rootComments.add(CommentGetRespVO);
        } else {
            // 子评论，找到父评论并添加到其children中
            CommentGetRespVO parent = commentMap.get(parentId);
            if (parent != null) {
                parent.getChildren().add(CommentGetRespVO);
            }
        }
    }
    
    return rootComments;
}

/**
 * 将Comment转换为CommentGetRespVO
 *
 * @param comment 原始评论实体
 * @return CommentGetRespVO 评论VO对象
 */
private CommentGetRespVO convertToVO(Comment comment) {
    CommentGetRespVO vo = new CommentGetRespVO();
    vo.setId(comment.getId());
    vo.setUserId(comment.getUserId());
    vo.setPostId(comment.getPostId());
    vo.setContent(comment.getContent());
    vo.setParentId(comment.getParentId());
    vo.setCreatedAt(comment.getCreatedAt());
    // 初始化children列表
    vo.setChildren(new ArrayList<>());
    return vo;
}





}
