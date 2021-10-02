function cifrado() {
    var radios = document.getElementsByName('radios');
    var selects = document.getElementById('idioma');
    if (radios[0].checked) {

        if (selects.options[selects.selectedIndex].text == "Español") {
            cifradoEs();
        }
    }
    else if (radios[1].checked) {

        if (selects.options[selects.selectedIndex].text == "Español") {
            descifradoEs();
        }
    }
}
function validarAscii(caracter, tamAlfa, alfabeto) {
    for (var i = 0; i < tamAlfa; i++) {
        if (caracter == alfabeto[i]) {
            return true; //es una letra del alfabeto
        }
    }
    return false;	//es un simbolo o espacio

}

function cifradoEs() {
    var tamAlfabe = 27;
    var texto = (document.getElementById('texto').value).toLowerCase();
    var tamTexto = texto.length;
    var clave = (document.getElementById('clave').value).toLowerCase();
    console.log(texto);
    console.log(clave);
    var contClave = 0;
    //guardo el abecedario en espa;ol 

    var alfaIng = new Array();
    var contEs = 0;
    for (var i = 0; i < tamAlfabe; i++) {
        if (i == 14) {
            alfaIng[i] = String.fromCharCode(241);
        }
        else {
            alfaIng[i] = String.fromCharCode(97 + contEs);
            contEs++;
        }
        console.log(alfaIng[i]);
    }
    var valorClave = new Array();
    var tamClave = clave.length;
    var contaClave = 0;
    for (var i = 0; i < tamClave; i++) {
        for (var j = 0; j < tamAlfabe; j++) {
            if (clave[i] == alfaIng[j]) {
                valorClave[contaClave] = j;
                contaClave++;
                //console.log(j);
            }
        }
        //console.log("letra"+i);

    }
    //Asignando el valor ascii al texto
    var valorTexto = new Array();
    for (var i = 0; i < tamTexto; i++) {
        for (var j = 0; j < tamAlfabe; j++) {
            if (texto[i] == " ") {
                valorTexto[i] = " ";
            }
            else if (texto[i] == alfaIng[j]) {
                valorTexto[i] = j;
            }

        }

    }

    //cifrando
    var textoCifrado = new Array();
    for (var i = 0; i < tamTexto; i++) {
        //espacio
        if (validarAscii(texto[i], tamAlfabe, alfaIng)) {
            textoCifrado[i] = alfaIng[(valorTexto[i] + valorClave[contClave]) % tamAlfabe];
            contClave++;
        }
        else {
            textoCifrado[i] = texto[i];
        }

        if (contClave == (valorClave.length)) {

            contClave = 0;
        }
    }
    div = document.getElementById("resultado1");
    var cadena = "";
    for (var i = 0; i < tamTexto; i++) {
        cadena += textoCifrado[i];
    }
    div.textContent = 'El mensaje cifrado es: ' + cadena;
    console.log(cadena);

}
function descifradoEs() {
    var tamAlfabe = 27;
    var texto = (document.getElementById('texto').value).toLowerCase();
    var tamTexto = texto.length;
    var clave = (document.getElementById('clave').value).toLowerCase();
    console.log(texto);
    console.log(clave);
    var contClave = 0;
    //guardo el abecedario en espa;ol 

    var alfaIng = new Array();
    var contEs = 0;
    for (var i = 0; i < tamAlfabe; i++) {
        if (i == 14) {
            alfaIng[i] = String.fromCharCode(241);
        }
        else {
            alfaIng[i] = String.fromCharCode(97 + contEs);
            contEs++;
        }
        console.log(alfaIng[i]);
    }

    // asignando el valor ascii a la clave
    var valorClave = new Array();
    var tamClave = clave.length;
    var contaClave = 0;
    for (var i = 0; i < tamClave; i++) {
        for (var j = 0; j < tamAlfabe; j++) {
            if (clave[i] == alfaIng[j]) {
                valorClave[contaClave] = j;
                contaClave++;
                //console.log(j);
            }
        }
        //console.log("letra"+i);

    }
    //Asignando el valor ascii al texto
    var valorTexto = new Array();
    for (var i = 0; i < tamTexto; i++) {
        for (var j = 0; j < tamAlfabe; j++) {
            if (texto[i] == " ") {
                valorTexto[i] = " ";
            }
            else if (texto[i] == alfaIng[j]) {
                valorTexto[i] = j;
            }

        }

    }

    //descifrando
    var textoCifrado = new Array();
    for (var i = 0; i < tamTexto; i++) {
        //espacio
        if (validarAscii(texto[i], tamAlfabe, alfaIng) && (valorTexto[i] - valorClave[contClave]) >= 0) {
            textoCifrado[i] = alfaIng[(valorTexto[i] - valorClave[contClave]) % tamAlfabe];
            contClave++;
        }
        else if (validarAscii(texto[i], tamAlfabe, alfaIng) && (valorTexto[i] - valorClave[contClave]) < 0) {
            textoCifrado[i] = alfaIng[(valorTexto[i] - valorClave[contClave] + tamAlfabe) % tamAlfabe];
            contClave++;
        }
        else {
            textoCifrado[i] = texto[i];
        }
        if (contClave == valorClave.length) {
            contClave = 0;
        }
    }

    div = document.getElementById("resultado1");
    var cadena = "";
    for (var i = 0; i < tamTexto; i++) {
        cadena += textoCifrado[i];
    }
    div.textContent = 'El mensaje descifrado es: ' + cadena;
    console.log(cadena);

}