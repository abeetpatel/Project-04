$(document).ready(function() {
	$("#sdate").dateselecter({
		dateFormat : 'dd-mm-yy',
		changeMonth : true,
		changeYear : true,
		yearRange : '2024:2026',
		minDate : new Date(2024, 12, 31),
		maxDate : new Date(2026, 12, 31)
	});
});