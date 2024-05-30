function handleLikeClick(button){
    const newsId = $(button).data("newsId");
    const likeCountElement = $(button).parent().find(".like-count");
    const liked = $(button).data("isLiked")

    if(isLiked){
        $.ajax({
            url: `/detail/unlike`,
            method: "POST",
            success: function(data){
                $(button).removeClass("liked");
                $(button).data("isLiked", !isLiked);
                $(button).text("点赞");
                getLikeCount(newsId, likeCountElement);
            },
        });
    } else {
        $.ajax({
            url: "/detail/like?newsId=" + newsId,
            method: "POST",
            success: function(data){
                $(button).removeClass("liked");
                $(button).data("isLiked", !isLiked);
                $(button).text("已点赞");
                getLikeCount(newsId, likeCountElement);
            },
        });
    }
}