$(function() {

	//popup edit employee
	$('table #editButton').on('click', function(event) {
		event.preventDefault();

		// employee/findById/?id=1
		var href = $(this).attr('href');

		$.get(href, function(employee, status) {
			$('#idEdit').val(employee.id);
			$('#firstnameEdit').val(employee.firstname);
			$('#lastnameEdit').val(employee.lastname);
			$('#emailEdit').val(employee.email);
			$('#designationEdit').val(employee.designation);

		});
		$('#editEmployeeModal').modal();
	});

	//popup edit job title
	$('table #editJobBtn').on('click', function(event) {
		event.preventDefault();

		var href = $(this).attr('href');

		$.get(href, function(job, status) {
			$('#jobIdEdit').val(job.id);
			$('#nameEdit').val(job.name);
		});
		$('#updateDesignationModal').modal();
	});
	
	
	var errorBanner = $('#error-Banner');
	var infoBanner = $('#info-Banner');
	var warningBanner = $('#warning-Banner');
	
	if (errorBanner != null) {
		errorBanner.fadeOut(10000);
	}
	if (infoBanner != null) {
		infoBanner.fadeOut(10000);
	}
	if (warningBanner != null) {
		warningBanner.fadeOut(10000);
	}
	
	// Display the delete Employee modal
	$('table #deleteButton').on('click', function(event) {
		event.preventDefault();

		var href = $(this).attr('href');

		$('#yesDeleteEmployee').attr('href', href);

		$('#deleteEmployeeModal').modal();
	});

});