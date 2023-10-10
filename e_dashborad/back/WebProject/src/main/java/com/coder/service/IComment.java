package com.coder.service;

import com.coder.Entity.CommentDto;
import org.w3c.dom.Comment;

public interface IComment {

        CommentDto createComment(CommentDto commentDto,Integer userId,Integer postId);

        void deleteComment(Integer id);

}
