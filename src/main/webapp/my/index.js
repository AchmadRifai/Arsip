function pencarian(a){
    p=$('#pencarian');
    p.text('');
    for(x=0;x<a.length;x++){
        o=a[x];
        p.append("<div class='ui column card'>");
        p.append("<div class='image'>");
        p.append("<img src='gbr/"+a.tipe+"'>");
        p.append("</div><div class='content'>");
        p.append("<a class='header' href='berkas.data?akses="+o.url+"'>"+o.nama+"</a>");
        p.append("<div class='meta'><span class='date'>"+o.tgl+"</span></div>");
        p.append("<div class='description'><a href='#'>Wedoke</a></div></div></div>");
    }
}function paginator(no,size){
    p=$('#pagi');
    p.text("<a class='icon item' id='mundur'><i class='left arrow icon'></i></a>");
    batas=10;
    if(size<10)batas=size;
    for(x=1;x<=batas;x++){
        p.append("<a class='");
        if(no===x)p.append('active');
        p.append("item'>"+x+"</a>");
    }if(size>10)p.append("<a class='disabled item'>...</a>");
    p.append("<a class='icon item' id='maju'><i class='right arrow icon'></i></a>");
}
function muat(o){
    pencarian(o.a);
    paginator(o.no,o.size);
    if($('#loader').hasClass('active'))$('#loader').removeClass('active');
}
function loadAll(no){
    if(""!==$('#cariBerkas').text())$.getJSON('cariBerkas.json?kunci='+$('#cariBerkas').text()+'&no='+no,function(o){
        muat(o);
    });else $.getJSON('cariBerkas.json?no='+no,function(o){muat(o);});
    if(!$('#loader').hasClass('active'))$('#loader').toggleClass('active');
}
$(document).ready(function(){
    $('#cariBerkas').keyup(function(){
        loadAll(1);
    });loadAll(1);
});