$(document).ready(function(){
    $('#cariBerkas').keyup(function(){
        if(!$('#loader').hasClass('active'))$('#loader').toggleClass('active');
        p=$('#pencarian');
        p.html("");
        if(''!==$('#cariBerkas').text()){
            $.getJSON('cariBerkas.json?kunci='+$('#cariBerkas').text(),function(intuk){
            p.append("<div class='ui column card'>");
            p.append("<div class='image'><img src='gbr/"+intuk.tipe+"'>");
            p.append("<div class='content'><a class='header' href='berkas.data?akses="+intuk.berkas+"'>"+intuk.nama+"</a>");
            p.append("<div class='meta'><span class='date'>"+intuk.tgl+"</span></div>");
            p.append("<div class='description'><a href='akun.html?akun="+intuk.akun+"'>"+intuk.nama_upload+"</a></div>");
            p.append("</div>");});
        }else $.getJSON('cariBerkas.json',function(intuk){
            p.append("<div class='ui column card'>");
            p.append("<div class='image'><img src='gbr/"+intuk.tipe+"'>");
            p.append("<div class='content'><a class='header' href='berkas.data?akses="+intuk.berkas+"'>"+intuk.nama+"</a>");
            p.append("<div class='meta'><span class='date'>"+intuk.tgl+"</span></div>");
            p.append("<div class='description'><a href='akun.html?akun="+intuk.akun+"'>"+intuk.nama_upload+"</a></div>");
            p.append("</div>");});
        if($('#loader').hasClass('active'))$('#loader').removeClass('active');
    });
});