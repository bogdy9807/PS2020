$(document).ready(function () {
  var $username = $("#username");
  var $password = $("#password");
  var $response_info = $("#response");
  var $email = $("#email");
  var $name = $("#name");
  var $typeofuser = $("#usertype");

  $("#register").click(function (e) {
    var usertype = parseInt($typeofuser.val(), 10);

    $.ajax({
      type: "POST",
      url: "http://localhost:8080/register",
      data: {
        username: $username.val(),
        password: $password.val(),
        inputUserType: usertype,
        email: $email.val(),
        nume: $name.val(),
      },
      dataType: "text",
      success: function (data) {
        if (parseInt(data, 10) === -1) {
          $response_info.append(
            "<li>Username-ul introdus este deja utilizat!</li>"
          );
        } else {
          if (parseInt(data, 10) == -2) {
            $response_info.append(
              "<li>Exista deja un cont pe acest email!</li>"
            );
          } else {
            $response_info.append("<li>Contul a fost creat!</li>");
          }
        }
      },
      error: function () {
        $("#register").html("error");
      },
    });
  });
});
