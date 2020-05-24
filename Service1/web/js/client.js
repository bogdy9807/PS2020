$(document).ready(function()    {
	
	var $response = $('#response');
	var $info = $('#infoProg');
	var $data = $('#dataID');
	var $service_id = $('#serviceID');
	
    $.ajax({
        type: 'GET',
        url:'http://localhost:8080/getCurrentUserName',
        success: function(data){
			 document.getElementById('outputName').innerHTML = data;
		},
        error: function(){
            $("#id").html("error");
        }
    });
	
	$("#viewBooksBtn").click(function(e)    {
        $.ajax({
            type: 'GET',
            url:'http://localhost:8080/showBooks',
			dataType: 'json',
            success: function(data){
				$response.empty();
				$response.append('<li>Programari: </li>');
                $.each(data, function (i, prog) {
					$response.append('<li>Numar programare: ' + prog.id + ', Numar service: ' + prog.service_id + ', Data programare: ' + prog.data_programare + ', Data creare ' + prog.data_creare + ', Cost estimat: ' + prog.cost_estimat + ', Data estimata finalizare: ' + prog.data_estimata_fin + '</li>');
                })
			},
            error: function(){
                $("#prog").html("error");
            }
		
        });
    });
	
	$("#showOffersBtn").click(function(e)    {
        $.ajax({
            type: 'GET',
            url:'http://localhost:8080/afiseazaService',
			dataType: 'json',
            success: function(data){
				$response.empty();
				$response.append('<li>Service-uri disponibile: </li>');
                $.each(data, function (i, service) {
					if(service.id != 0){
						$response.append('<li>Numar: ' + service.id + ', Nume: ' + service.nume + ', Informatii: ' + service.informatii + '</li>');
					}
				})
			},
            error: function(){
                $("#service").html("error");
            }
		
        });
    });

	$("#emitereBtn").click(function(e)    {
		
        $.ajax({
            type: 'POST',
            url:'http://localhost:8080/emiteCerereProgramare',
			data: {serviceId: parseInt($service_id.val(), 10), data_prog: $data.val(), info: $info.val()},
            dataType: 'text',
			success: function(data){
				if(parseInt(data, 10) === 0){
					$response.empty();
					$response.append('<li>Cerere emisa!</li>');
				}
				else{
					$response.empty();
					$response.append('<li>Service-ul cu numarul' + $service_id + 'nu a fost gasit!</li>');
				}
			},
            error: function(){
                $("#cerere").html("error");
            }
		
        });
    });

});