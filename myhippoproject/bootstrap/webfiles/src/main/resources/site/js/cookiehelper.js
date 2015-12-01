//添加cookie 
//name 值不要有空格
function addCookie(name,value,expires,path,domain){ 
	var str=name+"="+escape(value);
	if(expires!=""){
		var date=new Date();
		date.setTime(date.getTime()+expires*24*60*60*1000);//expires单位为天 
		str+=";expires="+date.toGMTString();
	}
	if(path!=""){
		str+=";path="+path;//指定可访问cookie的目录 
	}
	if(domain!=""){
		str+=";domain="+domain;//指定可访问cookie的域 
	}
	document.cookie=str;
}
//取得cookie 
function getCookie(name){ 
	var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
        return unescape(arr[2]); 
    else 
        return null; 
}
//删除cookie 
function delCookie(name){ 
//	var exp = new Date(); 
//    exp.setTime(exp.getTime()); 
//    var cval=getCookie(name); 
//    alert(name + "=n;expires="+exp.toGMTString());
//    document.cookie= name + "=n;expires="+exp.toGMTString();
	addCookie(name,"n","0","/","");
}
function clearCookie2()
{
	alert("123");
	foreach(); 
	alert("321");
}
//js 遍历所有Cookie
function foreach()
{
	var strCookie=document.cookie;
	var arrCookie=strCookie.split("; "); // 将多cookie切割为多个名/值对
	for(var i=0;i<arrCookie.length;i++)
	{ // 遍历cookie数组，处理每个cookie对
	    var arr=arrCookie[i].split("=");
	    if(arr.length>0)
	    DelCookie(arr[0]);
	}
}
function GetCookieVal(offset)
{
	var endstr = document.cookie.indexOf (";", offset);
	if (endstr == -1)
		endstr = document.cookie.length;
	return decodeURIComponent(document.cookie.substring(offset, endstr));
}
function DelCookie(name)
{
	var exp = new Date();
	exp.setTime (exp.getTime());
	var cval = GetCookie (name);
	addCookie(name,"n","0","/","");
}
 
function GetCookie(name)
{
	var arg = name + "=";
	var alen = arg.length;
	var clen = document.cookie.length;
	var i = 0;
	while (i < clen)
	{
		var j = i + alen;
		if (document.cookie.substring(i, j) == arg)
		return GetCookieVal (j);
		i = document.cookie.indexOf(" ", i) + 1;
		if (i == 0) 
			break;
	}
	return null;
}

