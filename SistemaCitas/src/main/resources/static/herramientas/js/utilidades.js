function Delete(id) {

	$.confirm({
		title: 'Eliminar contacto',
		content: '¿Desea eliminar al contacto?',
		animation: 'RotateXR',
		closeAnimation: 'scale',
		buttons: {
			add: {
				text: 'Aceptar',
				action: function() {
					window.location.href = '/dashboard-admin/eliminar/' + id
				}
			},
			cancel: {
				text: 'Cancelar',
				action: function() {

				}
			}
		}
	});

}


function soloLetrasCurpRfc(e) {
   key = e.keyCode || e.which;
   tecla = String.fromCharCode(key).toLowerCase();
   letras = "áéíóúabcdefghijklmnñopqrstuvwxyz0123456789";
   especiales = [];

   tecla_especial = false
   for (var i in especiales) {
      if (key == especiales[i]) {
         tecla_especial = true;
         break;
      }
   }

   if (letras.indexOf(tecla) == -1 && !tecla_especial)
      return false;
}

function soloLetras(e) {
   key = e.keyCode || e.which;
   tecla = String.fromCharCode(key).toLowerCase();
   letras = "áéíóúabcdefghijklmnñopqrstuvwxyz";
   especiales = [];

   tecla_especial = false
   for (var i in especiales) {
      if (key == especiales[i]) {
         tecla_especial = true;
         break;
      }
   }

   if (letras.indexOf(tecla) == -1 && !tecla_especial)
      return false;
}

function soloNumeros(e) {
   key = e.keyCode || e.which;
   tecla = String.fromCharCode(key).toLowerCase();
   letras = "0123456789";
   especiales = [];

   tecla_especial = false
   for (var i in especiales) {
      if (key == especiales[i]) {
         tecla_especial = true;
         break;
      }
   }

   if (letras.indexOf(tecla) == -1 && !tecla_especial)
      return false;
}

function soloCorreo(e) {
   key = e.keyCode || e.which;
   tecla = String.fromCharCode(key).toLowerCase();
   letras = "abcdefghijklmnñopqrstuvwxyz0123456789@_-.";
   especiales = [];

   tecla_especial = false
   for (var i in especiales) {
      if (key == especiales[i]) {
         tecla_especial = true;
         break;
      }
   }

   if (letras.indexOf(tecla) == -1 && !tecla_especial)
      return false;
}

function soloPassword(e) {
   key = e.keyCode || e.which;
   tecla = String.fromCharCode(key).toLowerCase();
   letras = "abcdefghijklmnñopqrstuvwxyz0123456789;_-.!";
   especiales = [];

   tecla_especial = false
   for (var i in especiales) {
      if (key == especiales[i]) {
         tecla_especial = true;
         break;
      }
   }

   if (letras.indexOf(tecla) == -1 && !tecla_especial)
      return false;
}

function soloDireccion(e) {
   key = e.keyCode || e.which;
   tecla = String.fromCharCode(key).toLowerCase();
   letras = "áéíóúabcdefghijklmnñopqrstuvwxyz  123456789,";
   especiales = [];

   tecla_especial = false
   for (var i in especiales) {
      if (key == especiales[i]) {
         tecla_especial = true;
         break;
      }
   }

   if (letras.indexOf(tecla) == -1 && !tecla_especial)
      return false;
}