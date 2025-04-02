$(document).ready(function() {
	$("#sdate").datepicker({
		dateFormat : 'dd-mm-yy',
		changeMonth : true,
		changeYear : true,
		yearRange : '2024:2026',
		minDate : new Date(2024, 0, 1),
		maxDate : new Date(2026, 11, 30)
	});
});