package com.gb.myeasyblog.controller;

import com.gb.myeasyblog.dto.PageReqDTO;
import com.gb.myeasyblog.dto.PostAddReqDTO;
import com.gb.myeasyblog.dto.PostModifyReq;
import com.gb.myeasyblog.entity.Post;
import com.gb.myeasyblog.service.PostService;
import com.gb.myeasyblog.util.PageResult;
import com.gb.myeasyblog.util.Result;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    /**
     * 保存新的帖子
     *
     * @param postAddReqDTO 帖子添加请求数据传输对象，包含帖子的标题、内容等信息
     * @return 返回保存结果，包含保存成功的帖子信息或错误信息
     */
    @PostMapping
    public Result<Post> save(@Valid @RequestBody PostAddReqDTO postAddReqDTO){
        return postService.save(postAddReqDTO);
    }



    /**
     * 获取所有帖子信息的分页列表
     *
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return 返回封装了分页结果的统一响应对象，其中包含帖子信息的分页数据
     */
    @GetMapping("/getAll")
    public Result<PageResult<Post>> getAll(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize){
        // 创建分页请求对象
        PageReqDTO pageReqDTO = new PageReqDTO();
        pageReqDTO.setPageNum(pageNum);
        pageReqDTO.setPageSize(pageSize);
        
        // 调用服务层获取所有帖子的分页数据
        PageResult<Post> result = postService.getAll(pageReqDTO);
        return Result.success(result);
    }


    /**
     * 根据ID获取帖子信息
     * @param id 帖子ID
     * @return 返回包含帖子信息的结果对象
     */
    @GetMapping("/{id}")
    public Result<Post> getById(@PathVariable("id") Integer id){
        return postService.getById(id);
    }


    /**
     * 修改帖子内容
     *
     * @param postModifyReq 帖子修改请求对象，包含要修改的帖子信息
     * @return 返回修改后的帖子结果封装对象
     */
    @PostMapping("/modify")
    public Result<Post> modify(@Valid @RequestBody PostModifyReq postModifyReq) {
        return postService.modify(postModifyReq);
    }


    /**
     * 删除指定ID的帖子
     *
     * @param id 帖子ID
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    public Result remove(@PathVariable("id") Integer id) {
        return postService.removeById(id);
    }


    /**
     * 根据用户ID分页获取帖子列表
     *
     * @param pageReqDTO 分页请求参数对象，包含分页信息和用户ID
     * @return 返回分页帖子列表的结果封装
     */
    @PostMapping("/users/id")
    public Result<PageResult<Post>> getPostsByUserId(@Valid @RequestBody PageReqDTO pageReqDTO){
        // 调用服务层方法根据用户ID分页查询帖子列表
        PageResult<Post> result = postService.getPostsByUserId(pageReqDTO);
        // 将查询结果封装成统一返回格式
        return Result.success(result);
    }










}
