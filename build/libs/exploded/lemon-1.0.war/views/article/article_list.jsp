<%--
  Created by IntelliJ IDEA.
  User: jyj
  Date: 2017/8/23
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../layout/include.jsp" %>
<link href="<%=request.getContextPath()%>/css/favorites/article.css" rel="stylesheet"/>

<script src="<%=request.getContextPath()%>/js/favorites/article.js"></script>
<div class="title">文章列表</div>


<blockquote class="layui-elem-quote news_search">
    <div class="layui-inline">
        <div class="layui-input-inline">
            <input type="text" placeholder="请输入关键字" class="layui-input search_input"/>
        </div>
        <a class="layui-btn search_btn" lay-filter="articleSearchFilter" lay-submit>查询</a>
    </div>
    <div class="layui-inline">
        <a class="layui-btn layui-btn-normal articleAdd_btn">添加文章</a>
    </div>
    <div class="layui-inline">
        <a class="layui-btn recommend" style="background-color: #5FB878">推荐文章</a>
    </div>
    <div class="layui-inline">
        <a class="layui-btn audit_btn">审核文章</a>
    </div>
    <div class="layui-inline">
        <a class="layui-btn layui-btn-danger batchDel">批量删除</a>
    </div>
</blockquote>


<table class="layui-table" id="article_table_list" lay-filter="article_table_id">
</table>


<script type="text/javascript">
    layui.config({
        base: "${ctx}/js/"
    }).use(['form', 'table', 'layer', 'common'], function () {
        var $ = layui.$,
            form = layui.form,
            table = layui.table,
            layer = layui.layer,
            common = layui.common;

        /**表格加载*/
        table.render({
            elem: '#article_table_list',
            url: '${ctx}/article/list',
            id: 'article_table_id',
            height: 'full-140',
            width: '1697',
            skin: 'row',
            even: 'true',
            cols: [[
                {checkbox: true, fixed: 'left', width: '60'},
                {field: 'id', title: 'ID', width: '200'},
                {field: 'title', title: '文章标题', width: '250'},
                {field: 'author', title: '发布人', width: '230'},
                {field: 'style', title: '类型', width: '230'},
                /*{field: 'status', title: '审核状态', width: '230'},
                {field: 'visit', title: '浏览权限', width: '230'},*/
                {field: 'publishTime', title: '发布时间', width: '250'},
                {fixed: 'right', title: '操作', align: 'center', width: '250', toolbar: '#articleBar'}
            ]],
            initSort: {
                field: 'id' //排序字段，对应 cols 设定的各字段名
                , type: 'desc' //排序方式  asc: 升序、desc: 降序、null: 默认排序
            }
        });

        //查询
        $(".search_btn").click(function () {
            //监听提交
            form.on('submit(articleSearchFilter)', function (data) {
                table.reload('article_table_id', {
                    where: {
                        searchTerm: data.field.searchTerm,
                        searchContent: data.field.searchContent
                    },
                    height: '140px'
                });
            });

        });



        //新增
        $(".articleAdd_btn").click(function () {
            var url = "${ctx}/views/article/atricle_edit.jsp";
            common.cmsLayOpen('新增', url, '550px', '340px');
        });

        /**监听工具条*/
        table.on('tool(article_table_id)', function(obj){
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值

            //公告详情
            if(layEvent === 'article_detail') {
                var articleId = data.id;
                var url =  "${ctx}/article/detail?articleId="+articleId;
                common.cmsLayOpenTip('文章详情',url,'100%','100%');

                //公告删除
            }else if(layEvent === 'article_delete') {
                var articleId = data.id;
                var url = "${ctx}/article/del";
                var param = {articleId:articleId};
                common.ajaxCmsConfirm('系统提示', '确定要删除当前文章吗?',url,param);

            }
        });
    });


</script>

<!--工具条 -->
<script type="text/html" id="articleBar">
    <div class="layui-btn-group">
        <a class="layui-btn layui-btn-mini layui-btn-info  article_detail" lay-event="article_detail"><i class="layui-icon larry-icon larry-chaxun2"></i>详情</a>
        <shiro:hasPermission name="eTDnjGAM">
            <a class="layui-btn layui-btn-mini layui-btn-danger article_delete" lay-event="article_delete"><i class="layui-icon larry-icon larry-ttpodicon"></i>删除</a>
        </shiro:hasPermission>
    </div>
</script>


