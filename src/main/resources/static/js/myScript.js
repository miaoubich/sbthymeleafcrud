$(function() {

	// popup edit employee
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

	// popup edit job title
	$('table #editJobBtn').on('click', function(event) {
		event.preventDefault();

		var href = $(this).attr('href');

		$.get(href, function(job, status) {
			$('#jobIdEdit').val(job.id);
			$('#nameEdit').val(job.name);
		});
		$('#updateDesignationModal').modal();
	});

	/*
	 * 
	 * Message Banners
	 */

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

	/* Add new employee form validation */
	var addEmployeeForm = $('#addEmployeeForm');
	var firstname = $('#firstname');
	var lastname = $('#lastname');
	var email = $('#email');

	
	if (addEmployeeForm.length) {
		addEmployeeForm.validate({
			rules : {
				firstname : {
					required : true
				},
				lastname:{
					required: true
				},
				email:{
					required: true,
					   email: true
				},
				designationid:{
					required: true
				}
			},
			messages : {
				firstname : {
					required : 'Firstname is mandatory!'
				},
				lastname:{
					required: 'Lastname is mandatory!'
				},
				email:{
					required: 'Email is mandatory!',
					   email: 'Please enter a valid email!'
				},
				designationid:{
					required: 'Job title is mandatory!'
				}
			}

		});
	}

	$(document).click(function() {
		if(firstname.val() != "" && lastname.val() != "" && email.val() != ""){
			$('button[type="submit"]').removeAttr('disabled');
		}
	});
	
	
	/*
	 * Designation form validation
	 * */
	var addDesignationForm = $('#addDesignationForm');
	var $name = $('#jobtitle');

	$name.focusout(function(){
		console.log("Focused OUT Name field!");
	});
	
	if (addDesignationForm.length) {
		addDesignationForm.validate({
			rules : {
				name:{
					required: true
				}
			},
			messages : {
				name : {
					required: 'Job title is mandatory!'
				}
			}

		});
	}
	
});