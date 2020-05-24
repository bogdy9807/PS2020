$(document).ready(function () {
  var $id = $("#idDelete");
  var $response = $("#response");

  $("#afisareClientiBtn").click(function (e) {
    $.ajax({
      type: "GET",
      url: "http://localhost:8080/afiseazaClienti",
      dataType: "json",
      success: function (data) {
        $response.empty();
        $response.append("<li>Clienti: </li>");
        $.each(data, function (i, client) {
          $response.append(
            "<li>Client ID: " +
              client.id +
              ", Nume: " +
              client.nume +
              ", Data inregistrare: " +
              client.datainreg +
              ", Email:  " +
              client.email +
              "</li>"
          );
        });
      },
      error: function () {
        $("#showclienti").html("error");
      },
    });
  });

  $("#afisareLoginBtn").click(function (e) {
    $.ajax({
      type: "GET",
      url: "http://localhost:8080/afiseazaLogin",
      dataType: "json",
      success: function (data) {
        $response.empty();
        $response.append("<li>Login: </li>");
        $.each(data, function (i, login) {
          $response.append(
            "<li>Login ID: " +
              login.id +
              ", Usertype: " +
              login.usertype +
              ", Username: " +
              login.username +
              ", Password:  " +
              login.password +
              ", ID client: " +
              login.id_client +
              ", ID prestator: " +
              login.id_prestator +
              "</li>"
          );
        });
      },
      error: function () {
        $("#showlogin").html("error");
      },
    });
  });

  $("#afisarePrestatoriBtn").click(function (e) {
    $.ajax({
      type: "GET",
      url: "http://localhost:8080/afiseazaPrestatori",
      dataType: "json",
      success: function (data) {
        $response.empty();
        $response.append("<li>Prestatori: </li>");
        $.each(data, function (i, client) {
          $response.append(
            "<li>Prestator ID: " +
              client.id +
              ", Nume: " +
              client.nume +
              ", Data inregistrare: " +
              client.data_inreg +
              ", Email:  " +
              client.email +
              "</li>"
          );
        });
      },
      error: function () {
        $("#showprestatori").html("error");
      },
    });
  });

  $("#afisareProgramariBtn").click(function (e) {
    $.ajax({
      type: "GET",
      url: "http://localhost:8080/afiseazaProgramari",
      dataType: "json",
      success: function (data) {
        $response.empty();
        $response.append("<li>Programari: </li>");
        $.each(data, function (i, prog) {
          $response.append(
            "<li>Programare ID: " +
              prog.id +
              ", Service ID: " +
              prog.service_id +
              ", Client ID: " +
              prog.client_id +
              ", Data programare:  " +
              prog.data_programare +
              ", Data creare:  " +
              prog.data_creare +
              ", Cost estimat:  " +
              prog.cost_estimat +
              ", Data estimata finalizare:  " +
              prog.data_estimata_fin +
              "</li>"
          );
        });
      },
      error: function () {
        $("#showprog").html("error");
      },
    });
  });

  $("#afisareServiceBtn").click(function (e) {
    $.ajax({
      type: "GET",
      url: "http://localhost:8080/afiseazaService",
      dataType: "json",
      success: function (data) {
        $response.empty();
        $response.append("<li>Service-uri: </li>");
        $.each(data, function (i, serv) {
          $response.append(
            "<li>Service ID: " +
              serv.id +
              ", Nume: " +
              serv.nume +
              ", Prestator ID: " +
              serv.prestator_id +
              ", Informatii:  " +
              serv.informatii +
              "</li>"
          );
        });
      },
      error: function () {
        $("#showservice").html("error");
      },
    });
  });

  $("#deleteClientBtn").click(function (e) {
    $.ajax({
      type: "POST",
      url: "http://localhost:8080/deleteClient",
      data: { id_client: parseInt($id.val(), 10) },
      dataType: "text",
      success: function (data) {
        $response.empty();
        if (parseInt(data, 10) === -1) {
          $response.append("<li>Clientul nu a fost gasit!</li>");
        } else {
          $response.append("<li>Clientul a fost sters!</li>");
        }
      },
      error: function () {
        $("#deleteclient").html("error");
      },
    });
  });

  $("#deleteLoginBtn").click(function (e) {
    $.ajax({
      type: "POST",
      url: "http://localhost:8080/deleteLogin",
      data: { id_login: parseInt($id.val(), 10) },
      dataType: "text",
      success: function (data) {
        $response.empty();
        if (parseInt(data, 10) === -1) {
          $response.append("<li>Contul nu a fost gasit!</li>");
        } else {
          $response.append("<li>Contul a fost sters!</li>");
        }
      },
      error: function () {
        $("#deletelogin").html("error");
      },
    });
  });

  $("#deletePrestatorBtn").click(function (e) {
    $.ajax({
      type: "POST",
      url: "http://localhost:8080/deletePrestator",
      data: { id_prestator: parseInt($id.val(), 10) },
      dataType: "text",
      success: function (data) {
        $response.empty();
        if (parseInt(data, 10) === -1) {
          $response.append("<li>Prestatorul nu a fost gasit!</li>");
        } else {
          $response.append("<li>Prestatorul a fost sters!</li>");
        }
      },
      error: function () {
        $("#deleteprestator").html("error");
      },
    });
  });

  $("#deleteServiceBtn").click(function (e) {
    $.ajax({
      type: "POST",
      url: "http://localhost:8080/deleteService",
      data: { id_service: parseInt($id.val(), 10) },
      dataType: "text",
      success: function (data) {
        $response.empty();
        if (parseInt(data, 10) === -1) {
          $response.append("<li>Service-ul nu a fost gasit!</li>");
        } else {
          $response.append("<li>Service-ul a fost sters!</li>");
        }
      },
      error: function () {
        $("#deleteservice").html("error");
      },
    });
  });

  $("#deleteProgramareBtn").click(function (e) {
    $.ajax({
      type: "POST",
      url: "http://localhost:8080/deleteProgramare",
      data: { id: parseInt($id.val(), 10) },
      dataType: "text",
      success: function (data) {
        $response.empty();
        if (parseInt(data, 10) === -1) {
          $response.append("<li>Programarea nu a fost gasita!</li>");
        } else {
          $response.append("<li>Programarea a fost stearsa!</li>");
        }
      },
      error: function () {
        $("#deleteprogramare").html("error");
      },
    });
  });
});
