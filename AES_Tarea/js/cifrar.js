const cifrar = () => {
    var tipocif = document.form.tipoAES.value
    var llave = document.getElementById('llave').value.toString()
    var mensaje = document.getElementById('mensaje').value

    switch (tipocif) {
        case "128":

            if (llave.length <= 16) {
                llave = llave.padStart(16, 'abc');
            }

            else {
                swal("La llave tiene que ser de 16 caracteres", "error");
            }
            break

        case "192":
            if (llave.length <= 24) {
                llave = llave.padStart(24, 'abc')
            }

            else {
                swal("La llave tiene que ser de 24 caracteres", "error");
            }
            break

        case "256":
            if (llave.length <= 32) {
                llave = llave.padStart(32, 'abc')
            }

            else {
                swal("La llave tiene que ser de 32 caracteres", "error");
            }
            break
    }
    let menCif = CryptoJS.AES.encrypt(mensaje, llave)
    console.log(llave)
    document.getElementById('cifrado').innerHTML = menCif
    let blob = new Blob([menCif], { type: 'text/plain' })
    const href = URL.createObjectURL(blob);
    const a = Object.assign(document.createElement('a'), {
        href,
        style: "display:none",
        download: "Cifrado.txt"
    })
    document.body.appendChild(a)

    a.click();
    URL.revokeObjectURL(href)
    a.remove()
    return false;
}