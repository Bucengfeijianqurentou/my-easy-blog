package com.gb.myeasyblog.service.impl;

import com.gb.myeasyblog.dto.PageReqDTO;
import com.gb.myeasyblog.dto.PostAddReqDTO;
import com.gb.myeasyblog.dto.PostModifyReq;
import com.gb.myeasyblog.entity.Post;
import com.gb.myeasyblog.mapper.PostMapper;
import com.gb.myeasyblog.service.PostService;
import com.gb.myeasyblog.util.HttpStatusConstants;
import com.gb.myeasyblog.util.PageResult;
import com.gb.myeasyblog.util.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {


    private final PostMapper postMapper;

    /**
     * 保存帖子信息
     *
     * @param postAddReqDTO 帖子添加请求数据传输对象，包含帖子的基本信息
     * @return Result<Post> 返回操作结果，成功时返回保存的帖子对象，失败时返回相应的错误信息
     */
    @Override
    public Result<Post> save(PostAddReqDTO postAddReqDTO) {

        // 参数校验
        if (Objects.isNull(postAddReqDTO)) {
            return Result.error(HttpStatusConstants.BAD_REQUEST, "请求参数有误");
        }

        // 创建帖子实体并复制属性
        Post post = new Post();
        BeanUtils.copyProperties(postAddReqDTO, post);

        // 执行数据库插入操作
        int insert = postMapper.insert(post);

        // 检查插入结果
        if (insert < 0) {
            return Result.error(HttpStatusConstants.INTERNAL_ERROR, "服务器内部错误");
        }

        // 返回成功结果
        return Result.success("添加成功", post);
    }


    /**
     * 获取所有帖子的分页结果
     *
     * @param pageReqDTO 分页请求参数对象，包含页码和每页大小信息
     * @return PageResult<Post> 分页结果对象，包含帖子列表及相关分页信息
     */
    @Override
    public PageResult<Post> getAll(PageReqDTO pageReqDTO) {
        int pageNum = pageReqDTO.getPageNum();
        int pageSize = pageReqDTO.getPageSize();

        // 设置分页参数
        PageHelper.startPage(pageNum, pageSize);

        // 查询所有帖子数据
        List<Post> page = postMapper.selectAllPosts();

        // 封装分页信息
        PageInfo<Post> postPageInfo = new PageInfo<>(page);

        //List<Post> list = postPageInfo.getList();

        return PageResult.of(postPageInfo);
    }


    /**
     * 根据ID获取文章信息
     * @param id 文章ID
     * @return 返回文章信息的结果对象，如果文章不存在则返回错误信息
     */
    @Override
    public Result<Post> getById(Integer id) {
        // 根据ID查询文章信息
        Post post = postMapper.selectById(id);
        if (Objects.isNull(post)) {
            // 文章不存在时返回错误结果
            return Result.error(HttpStatusConstants.INTERNAL_ERROR,"文章不存在");
        }
        // 返回成功结果
        return Result.success(post);
    }

    /**
     * 修改帖子信息
     *
     * @param postModifyReq 包含要修改的帖子信息的请求对象，必须包含帖子ID
     * @return 返回修改结果，成功时返回修改后的帖子信息，失败时返回错误信息
     */
    @Override
    public Result modify(PostModifyReq postModifyReq) {
        Long id = postModifyReq.getId();
        // 检查要修改的帖子是否存在
        if (Objects.isNull(postMapper.selectById(id.intValue()))) {
            return Result.error(HttpStatusConstants.INTERNAL_ERROR, "请求目标不存在");
        }

        // 复制属性并设置更新时间
        Post post = new Post();
        BeanUtils.copyProperties(postModifyReq, post);
        post.setUpdatedAt(LocalDateTime.now());
        // 执行更新操作
        int count = postMapper.update(post);
        if (count != 1) {
            return Result.error("更新失败");
        }
        // 返回更新后的帖子信息
        return Result.success(postMapper.selectById(id.intValue()));
    }


    /**
     * 根据ID删除帖子
     *
     * @param id 帖子ID
     * @return 删除结果，成功返回Result.success()，失败返回相应的错误信息
     */
    @Override
    public Result removeById(Integer id) {
        // 检查要删除的帖子是否存在
        if (Objects.isNull(postMapper.selectById(id.intValue()))) {
            return Result.error(HttpStatusConstants.INTERNAL_ERROR, "请求目标不存在");
        }

        // 执行删除操作
        int count = postMapper.deleteById(id);

        // 根据删除结果返回相应信息
        if (count == 1) {
            return Result.success();
        }

        return Result.error(HttpStatusConstants.INTERNAL_ERROR, "删除失败");
    }



}
