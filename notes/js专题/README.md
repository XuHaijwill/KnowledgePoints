# js使用指南

## 获取时间

```
function today(){
            var today=new Date();
            var h=today.getFullYear();
            var m=today.getMonth()+1;
            var d=today.getDate();
            var hh=today.getHours();
            var mm=today.getMinutes();
            var ss=today.getSeconds();
            m= m<10?"0"+m:m;
            d= d<10?"0"+d:d;
            hh = hh < 10 ? "0" + hh:hh;
            mm = mm < 10 ? "0" +  mm:mm;
            ss = ss < 10 ? "0" + ss:ss;
            return h+"-"+m+"-"+d+" "+hh+":"+mm+":"+ss;
        }
```

