"use strict";

$("[data-checkboxes]").each(function () {
  var me = $(this),
    group = me.data('checkboxes'),
    role = me.data('checkbox-role');

  me.change(function () {
    var all = $('[data-checkboxes="' + group + '"]:not([data-checkbox-role="dad"])'),
      checked = $('[data-checkboxes="' + group + '"]:not([data-checkbox-role="dad"]):checked'),
      dad = $('[data-checkboxes="' + group + '"][data-checkbox-role="dad"]'),
      total = all.length,
      checked_length = checked.length;

    if (role == 'dad') {
      if (me.is(':checked')) {
        all.prop('checked', true);
      } else {
        all.prop('checked', false);
      }
    } else {
      if (checked_length >= total) {
        dad.prop('checked', true);
      } else {
        dad.prop('checked', false);
      }
    }
  });
});

$("#table-1").dataTable({
  "columnDefs": [
    { "sortable": false, "targets": [2, 3] }
  ]
});
$("#table-2").dataTable({
  "columnDefs": [
    { "sortable": false, "targets": [0, 2, 3] }
  ],
  order: [[1, "asc"]] //column indexes is zero based

});
$('#save-stage').DataTable({
  "scrollX": true,
  stateSave: true
});
$('#tableExport').DataTable({
  dom: 'Bfrtip',
  buttons: [
    {
      extend: 'excelHtml5',
      text: '<i  class="fas fa-file-excel"></i> ',
      titleAttr: 'Exporter en Excel',
      className: 'btn btn-icon btn-success'
    },
    {
      extend: 'pdfHtml5',
      text: '<i class="fas fa-file-pdf"></i> ',
      titleAttr: 'Exporter en PDF',
      className: 'btn btn-icon btn-danger'
    },
    {
      extend: 'print',
      text: '<i class="fa fa-print"></i> ',
      titleAttr: 'Imprimer',
      className: 'btn btn-icon btn-info'
    }
  ]
});
