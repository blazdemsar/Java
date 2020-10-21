$(document).ready(function() {
	
	$(document).on('click', '.view-passenger', function() {
		
		/*$("#passengerForm").trigger("reset");
		
		var flightId = $(this).attr("data-flightId");
		alert(flightId);
		
		$("#flight_flightId").val(flightId);*/
		
		$("#passengerModal").modal("toggle");
	})
})