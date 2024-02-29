var shippingPriceList = [
    { id: 1, countryName: 'United States', priceOne: 10.00, priceThree: 15.00 },
    { id: 2, countryName: 'Canada', priceOne: 12.00, priceThree: 20.00 },
    { id: 3, countryName: 'United Kingdom', priceOne: 8.00, priceThree: 20.00 },
    { id: 4, countryName: 'Germany', priceOne: 9.00, priceThree: 22.50 },
    { id: 5, countryName: 'France', priceOne: 10.50, priceThree: 23.00 },
    { id: 6, countryName: 'Australia', priceOne: 11.50, priceThree: 24.50 },
    { id: 7, countryName: 'Japan', priceOne: 13.00, priceThree: 27.50 },
    { id: 8, countryName: 'Brazil', priceOne: 15.00, priceThree: 32.50 },
    { id: 9, countryName: 'India', priceOne: 7.50, priceThree: 15.75 },
    { id: 10, countryName: 'South Africa', priceOne: 14.00, priceThree: 30.00 },
    { id: 11, countryName: 'Mexico', priceOne: 12.50, priceThree: 25.25 },
    { id: 12, countryName: 'Italy', priceOne: 8.50, priceThree: 18.25 },
    { id: 13, countryName: 'Spain', priceOne: 9.50, priceThree: 20.75 },
    { id: 14, countryName: 'China', priceOne: 11.00, priceThree: 24.50 },
    { id: 15, countryName: 'Russia', priceOne: 13.50, priceThree: 27.75 },
    { id: 16, countryName: 'South Korea', priceOne: 10.50, priceThree: 23.25 },
    { id: 17, countryName: 'Argentina', priceOne: 16.00, priceThree: 34.00 },
    { id: 18, countryName: 'Netherlands', priceOne: 9.00, priceThree: 20.50 },
    { id: 19, countryName: 'Sweden', priceOne: 10.00, priceThree: 23.00 },
    { id: 20, countryName: 'Switzerland', priceOne: 11.50, priceThree: 25.75 },
    { id: 21, countryName: 'Norway', priceOne: 12.50, priceThree: 26.25 },
    { id: 22, countryName: 'Denmark', priceOne: 11.00, priceThree: 24.50 },
    { id: 23, countryName: 'Singapore', priceOne: 14.00, priceThree: 30.00 },
    { id: 24, countryName: 'Malaysia', priceOne: 13.50, priceThree: 28.75 },
    { id: 25, countryName: 'New Zealand', priceOne: 10.00, priceThree: 23.00 },
    { id: 26, countryName: 'Ireland', priceOne: 8.00, priceThree: 17.00 },
    { id: 27, countryName: 'Belgium', priceOne: 9.50, priceThree: 22.75 },
    { id: 28, countryName: 'Austria', priceOne: 10.50, priceThree: 23.25 },
    { id: 29, countryName: 'Portugal', priceOne: 8.50, priceThree: 20.25 },
    { id: 30, countryName: 'Poland', priceOne: 11.50, priceThree: 25.75 },
    { id: 31, countryName: 'Turkey', priceOne: 12.00, priceThree: 27.00 },
    { id: 32, countryName: 'Thailand', priceOne: 15.00, priceThree: 32.50 },
    { id: 33, countryName: 'Indonesia', priceOne: 12.50, priceThree: 26.25 },
    { id: 34, countryName: 'South Africa', priceOne: 14.00, priceThree: 29.00 },
    { id: 35, countryName: 'Saudi Arabia', priceOne: 16.00, priceThree: 32.00 },
    { id: 36, countryName: 'Israel', priceOne: 9.00, priceThree: 20.50 },
    { id: 37, countryName: 'Greece', priceOne: 8.50, priceThree: 20.25 },
    { id: 38, countryName: 'Czech Republic', priceOne: 10.50, priceThree: 22.25 },
    { id: 39, countryName: 'Hungary', priceOne: 11.00, priceThree: 25.50 },
    { id: 40, countryName: 'Finland', priceOne: 10.00, priceThree: 23.00 }
];

window.onload = function() {
    document.getElementById("modal").style.display = "block";

    setTimeout(function() {
        document.getElementById("modal").style.display = "none";
    }, 1200);

};

function getddl() {
    document.getElementById("modal").style.display = "block";
    setTimeout(function() {
        document.getElementById("modal").style.display = "none";
    }, 1000);
    var selectedCountryId = document.getElementById("mySelect").value;

    var selectedShippingPrice = shippingPriceList.find(function (shippingPrice) {
        return shippingPrice.id == selectedCountryId;
    });

    if (selectedShippingPrice) {
        document.getElementById("one").innerText = selectedShippingPrice.priceOne + '$';
        document.getElementById("three").innerText = selectedShippingPrice.priceThree + '$';
    } else {
        console.error("Selected country not found in shippingPriceList");
    }
}

$(function(){
    $('#text1').hide();
    $('#mySelect').on('change', function() {
        $('#text1').hide();
        setTimeout(function(){
            $('#text1').fadeIn('slow');
        }, 1000);
    });
});
$(document).ready(function(){
    $('#text1').hide();
    setTimeout(function(){
        $('#text1').fadeIn('slow');
    }, 1200);
});