$(document).ready(function () {

});


function updateLikes(username, tweetId) {

    // alert("\nusername : " + username
    //     + "\ntweetId : " + tweetId);

    var el = $("#likes"+tweetId);
    var numberOfLikes = parseInt(el.text());
    var url = "/likeTweet?username=" + username + "&tweetId=" + tweetId;

    $.ajax({
        type : "GET",
        contentType : "application/json",
        url :url,
        data : "",
        dataType : 'json',
        success : function(data, textStatus, xhr) {

            //console.log(el);

            if (xhr.status === 201){
                console.log(textStatus)
                console.log(data);

                el.text(numberOfLikes+1);
            }
            else {
                console.log(textStatus)
                el.text(numberOfLikes-1);
            }

        },
        error : function(e) {
            alert("Error!");
            console.log("ERROR: ", e);
        }
    });


}
