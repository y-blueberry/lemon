<%--
  Created by IntelliJ IDEA.
  User: jyj
  Date: 2017/8/23
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="<%=request.getContextPath()%>/css/person/person_list.css" rel="stylesheet" />

<script src="<%=request.getContextPath()%>/js/person/person_list.js"></script>
<table class="layui-table">
    <colgroup>
        <col width="20%">
        <col width="20%">
        <col width="20%">
        <col>
    </colgroup>
    <thead>
    <tr>
        <th>清</th>
        <th>风</th>
        <th>徐</th>
        <th>来</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>orange</td>
        <td>2016-11-29</td>
        <td>1</td>
        <td><input type="text" class="layui-input" placeholder="在绝望里 虔诚的人 都长出了翅膀"/></td>
    </tr>
    <tr>
        <td>apple</td>
        <td>2016-11-28</td>
        <td>2</td>>
        <td><input type="text" class="layui-input" placeholder="而长大以后 梦中飞舞的萤火虫，在我的身体 陪我做梦"/></td>
    </tr>
    <tr>
        <td>banana</td>
        <td>2016-11-29</td>
        <td>3</td>
        <td><input type="text" class="layui-input" placeholder="然后飞行更遥远的地方"/></td>
    </tr>
    <tr>
        <td>stryberry</td>
        <td>2016-11-28</td>
        <td>4</td>
        <td><input type="text" class="layui-input" placeholder="在黑夜里迷路的人才能找到方向"/></td>
    </tr>
    </tbody>
</table>

<button type="button" class="layui-btn layui-btn-primary btn_save">保存</button>
<button type="button" class="layui-btn layui-btn-primary btn_add_person">新增</button>