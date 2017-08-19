# ajaxOnProject
<h2>使用ajax与后台交互的例子</h2>
  <p>增删改查的实现，根据ID查询，后台返回JSONObject对象的String，如果查不到，后台返回map的错误信息。前台用jquery和正则验证</p>
  <p>如果是list的对象即查询所有，可以返回JSONArray对象的String</p>
  <p>后台使用的是java web的SSM框架</p>
  <p>原来的查list是用jsp做的，一个为了方便，二是实现前后端分离，后台只提供数据，所以使用ajax接收和发送数据</p>
  前台不需要parse,用data.value的形式即可，如果是list,使用data[0].value，
  这里需要[JSON遍历的知识](http://www.runoob.com/json/js-json-objects.html/)
<h3>效果如图</h3>
ID查询一个对象
![alt 单个查询结果](/images/selectOne.png/)
查询list
![alt list查询](/images/listObject.png/)
