$(function () {
    $("#jqGrid").jqGrid({
        url: '/admin/lasted-news',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'newsId', index: 'newsId', width: 50, key: true, hidden: true},
            {label: '标题', name: 'newsTitle', index: 'newsTitle', width: 100},
            {label: '预览图', name: 'newsCoverImage', index: 'newsCoverImage', width: 70, formatter: coverImageFormatter},
            {label: '浏览量', name: 'newsViews', index: 'newsViews', width: 60},
//            {label: '状态', name: 'newsStatus', index: 'newsStatus', width: 60},
//            {label: '状态', name: 'newsStatus', index: 'newsStatus', width: 60, formatter: statusFormatter},
            {label: '添加时间', name: 'createTime', index: 'createTime', width: 80}
//            {label: '详情', name: 'detail', index: 'detail', width: 60}
//            {label: '点赞', name: 'like', index: 'like', width: 60}
        ],
        height: 400,
        rowNum: 10,
        rowList: [10, 20, 50],
        styleUI: 'Bootstrap',
        loadtext: '信息读取中...',
        rownumbers: false,
        rownumWidth: 20,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "data.list",
            page: "data.currPage",
            total: "data.totalPage",
            records: "data.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order",
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });

    $(window).resize(function () {
        $("#jqGrid").setGridWidth($(".card-body").width());
    });

    function coverImageFormatter(cellvalue) {
        return "<img src='" + cellvalue + "' height=\"120\" width=\"160\" alt='coverImage'/>";
    }

//    function statusFormatter(cellvalue) {
//        if (cellvalue == 0) {
//            return "<button type=\"button\" class=\"btn btn-block btn-secondary btn-sm\" style=\"width: 50%;\">草稿</button>";
//        }
//        else if (cellvalue == 1) {
//            return "<button type=\"button\" class=\"btn btn-block btn-success btn-sm\" style=\"width: 50%;\">发布</button>";
//        }
//    }

});

/**
 * jqGrid重新加载
 */
function reload() {
    var page = $("#jqGrid1").jqGrid('getGridParam', 'page');
    $("#jqGrid").jqGrid('setGridParam', {
        page: page
    }).trigger("reloadGrid");
}

//function addNews() {
//    window.location.href = "/admin/news/edit";
//}

function editNews() {
    var id = getSelectedRow();
    if (id == null) {
        return;
    }
    window.location.href = "/admin/news/edit/" + id;
}

function findNews(query) {
    // 构造请求体
    fetch('/admin/search?keyword='+query, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        const resultContainer = document.getElementById('resultContainer');
        resultContainer.innerHTML = ''; // 清空之前的搜索结果
        data.forEach(result => {
            const div = document.createElement('div');
            div.textContent = result.title; // 假设结果包含'title'字段
            resultContainer.appendChild(div);
        });
    })
    .catch(error => console.error('Error:', error));
}


function deleteNews() {
    var ids = getSelectedRows();
    if (ids == null) {
        return;
    }
    swal({
        title: "确认弹框",
        text: "确认要删除数据吗?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    }).then((flag) => {
            if (flag) {
                $.ajax({
                    type: "POST",
                    url: "/admin/news/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.resultCode == 200) {
                            swal("删除成功", {
                                icon: "success",
                            });
                            $("#jqGrid").trigger("reloadGrid");
                        } else {
                            swal(r.message, {
                                icon: "error",
                            });
                        }
                    }
                });
            }
        }
    );
}