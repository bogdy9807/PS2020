$(document).ready(function () {
  var $numeServ = $("#numeService");
  var $infoServ = $("#infoService");
  var $response_info = $("#response");
  var $response_info_prog = $("#response2");
  var $progNr = $("#numarProg");
  var $costEstimat = $("#costEstimat");
  var $dataE = $("#dataEstimata");
  var $numar_oferta = $("#idDeleteService");
  var $numar_programare = $("#idDeleteBook");

  $.ajax({
    type: "GET",
    url: "http://localhost:8080/getCurrentUserName",
    success: function (data) {
      document.getElementById("outputName").innerHTML = data;
    },
    error: function () {
      $("#numeUser").html("error");
    },
  });

  $("#adaugaOfertaBtn").click(function (e) {
    $.ajax({
      type: "POST",
      url: "http://localhost:8080/insertService",
      data: { nume: $numeServ.val(), info: $infoServ.val() },
      dataType: "text",
      success: function (data) {
        $response_info_prog.empty();
        $response_info.empty();
        $response_info.append("<li>Oferta adaugata!</li>");
      },
      error: function () {
        $("#adaugaOferta").html("error");
      },
    });
  });

  $("#verificaProgBtn").click(function (e) {
    $.ajax({
      type: "GET",
      url: "http://localhost:8080/showBooks",
      dataType: "json",
      success: function (data) {
        $response_info_prog.empty();
        $response_info.empty();
        $response_info.append("<li>Programari actuale: </li>");
        $.each(data, function (i, prog) {
          $response_info.append(
            "<li>Numar programare: " +
              prog.id +
              ", Numar service: " +
              prog.service_id +
              ", Data programare: " +
              prog.data_programare +
              ", Data creare " +
              prog.data_creare +
              ", Cost estimat: " +
              prog.cost_estimat +
              ", Data estimata finalizare: " +
              prog.data_estimata_fin +
              "</li>"
          );
        });
      },
      error: function () {
        $("#verificareProg").html("error");
      },
    });
  });

  $("#verificaProgBtn2").click(function (e) {
    $.ajax({
      type: "GET",
      url: "http://localhost:8080/showWaitingBooks",
      dataType: "json",
      success: function (data) {
        $response_info.empty();
        $response_info.append("<li>Programari in asteptare: </li>");
        $.each(data, function (i, prog) {
          $response_info.append(
            "<li>Numar programare: " +
              prog.id +
              ", Data programare: " +
              prog.data_programare +
              ", Data creare " +
              prog.data_creare +
              "</li>"
          );
        });
      },
      error: function () {
        $("#verificareProgNeconf").html("error");
      },
    });

    $.ajax({
      type: "GET",
      url: "http://localhost:8080/showWaitingBooksInfo",
      dataType: "json",
      success: function (data) {
        $response_info_prog.empty();
        $response_info_prog.append("<li>Informatii programari: </li>");
        $.each(data, function (i, inform) {
          $response_info_prog.append(
            "<li> Programare numar: " + i + "  " + inform + "</li>"
          );
        });
      },
      error: function () {
        $("#verificareInformProg").html("error");
      },
    });
  });

  // ADAUGA INFORMATII PROGRAMARI IN ASTEPTARE!

  $("#adaugaProgramareBtn").click(function (e) {
    $.ajax({
      type: "POST",
      url: "http://localhost:8080/confirmaProgramare",
      data: {
        id: parseInt($progNr.val(), 10),
        cost: parseInt($costEstimat.val(), 10),
        data_estim_fin: $dataE.val(),
      },
      dataType: "text",
      success: function (data) {
        $response_info_prog.empty();
        if (parseInt(data, 10) === 0) {
          $response_info.empty();
          $response_info.append("<li>Programare adaugata!</li>");
        } else {
          $response_info.empty();
          $response_info.append(
            "<li>Programarea in asteptare nu a fost gasita! Verificati numarul programarii!</li>"
          );
        }
      },
      error: function () {
        $("#adaugareProg").html("error");
      },
    });
  });

  $("#showServicesBtn").click(function (e) {
    $.ajax({
      type: "GET",
      url: "http://localhost:8080/showServicePrestator",
      dataType: "json",
      success: function (data) {
        $response_info_prog.empty();
        $response_info.empty();
        $response_info.append("<li>Oferte detinute: </li>");
        $.each(data, function (i, serv) {
          $response_info.append(
            "<li> Numar service: " +
              serv.id +
              ", Nume: " +
              serv.nume +
              ", Informatii: " +
              serv.informatii +
              "</li>"
          );
        });
      },
      error: function () {
        $("#showServices").html("error");
      },
    });
  });

  $("#deleteServiceBtn").click(function (e) {
    $.ajax({
      type: "POST",
      url: "http://localhost:8080/deleteServiceByPrestator",
      data: { id_deleteservice: parseInt($numar_oferta.val(), 10) },
      dataType: "text",
      success: function (data) {
        $response_info_prog.empty();
        $response_info.empty();
        if (parseInt(data, 10) == -1) {
          $response_info.append(
            "<li>Service-ul nu a fost gasit sau nu detineti acest service!</li>"
          );
        } else {
          if (parseInt(data, 10) == 0) {
            $response_info.append("<li>Service-ul a fost sters!</li>");
          }
        }
      },
      error: function () {
        $("#deleteServiceByPrestator").html("error");
      },
    });
  });

  $("#deleteBookBtn").click(function (e) {
    $.ajax({
      type: "POST",
      url: "http://localhost:8080/deleteBookByPrestator",
      data: { id_deletebook: parseInt($numar_programare.val(), 10) },
      dataType: "text",
      success: function (data) {
        $response_info_prog.empty();
        $response_info.empty();
        if (parseInt(data, 10) == -1) {
          $response_info.append(
            "<li>Programarea nu a fost gasita! Vedeti ofertele dumneavoastra!</li>"
          );
        } else {
          if (parseInt(data, 10) == 0) {
            $response_info.append("<li>Programarea a fost stearsa!</li>");
          }
        }
      },
      error: function () {
        $("#deleteOfertaByPrestator").html("error");
      },
    });
  });
});
