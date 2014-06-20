$(function () {

    $('#datetimepicker1').datetimepicker({
        format:'MM/DD/YYYY',
        pickTime:false,
        showToday: true,
    });

    $('#timepicker').datetimepicker({
        format:'HH:mm:ss',
        pickDate:false,
        pickTime: true,                
        useMinutes: true,               
        useSeconds: true,
    });
});