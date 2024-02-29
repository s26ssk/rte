function validate(){
    let form = document.getElementById('form');
    let email = document.getElementById('email').value;
    let submitemail = document.getElementById('submitemail');
    let pattern = /^[^ ]+@[^ ]+\.[a-z]{2,3}$/;

    if(email.match(pattern))
    {
        form.classList.add('valid');
        submitemail.style.display = 'block';
    }
    else
    {
        form.classList.remove('valid');
        submitemail.style.display = 'none';
    }
}



var btn = document.getElementById("submitemail");
btn.onclick = function() {
    setTimeout(function () {
        document.querySelector(".box").style.display = "inline-block";
    }, 1500);
}

var thankYouDiv = document.querySelector('.thank-you');

if (thankYouDiv) {

    setTimeout(function () {
        thankYouDiv.style.display = 'none';
    }, 2000);
}