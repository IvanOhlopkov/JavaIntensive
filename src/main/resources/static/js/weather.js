$(function(){
    $('.submit').on('click', function() {
        let city = $('.input').val();
        $.post('/weather', {city: city}, function(response){
            if(city != null) {
                $('.input').val('');
                $('.forecast').css({display: 'flex'});
                $('.result').append(city + ':  ' + response + '<br/>')
            } else {
                alert('Введите город')
            }
        });
    });

});