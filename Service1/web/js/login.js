$(document).ready(function()    {
	
	var $username = $('#username');
	var $passwordd = $('#password');
	var $response_info = $('#response');
	
    $("#login").click(function(e)    {
        $.ajax({
            type: 'GET',
            url:'http://localhost:8080/login',
			data: {username: $username.val(), password: $passwordd.val()},
			dataType: 'text',
            success: function(data){
//				$response_info.append('<li>Usertype Result: ' + data + '</li>');
				if (parseInt(data, 10) === 1) {
					console.log("Succesfully logged in as Client");
					window.location.href = "clientOptions.html";
				}
				else{
					if(parseInt(data, 10) === 2){
						console.log("Succesfully logged in as Prestator");
						window.location.href = "prestatorOptions.html";
					}
					else{
						if(parseInt(data, 10) === 0){
							console.log("Succesfully logged in as Administrator");
							window.location.href = "adminOptions.html";
						}
						else{
							$response_info.append('<li>Incorrect username or password</li>');
						}
					}
				}
			},
            error: function(){
                $("#id").html("error");
            }
		
        });
    });
	
	$("#register").click(function(e)	{
		window.location.href = "register.html";
	});


});