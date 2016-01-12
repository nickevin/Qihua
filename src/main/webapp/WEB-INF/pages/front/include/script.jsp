<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/pages/front/include/import.jsp"%>

<%-- 
<script src="<%=basePath%>/resources/lib/jquery/jquery.js"></script>
<script src="<%=basePath%>/resources/lib/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=basePath%>/resources/lib/jquery/plugins/jquery.md5.js"></script>
<script src="<%=basePath%>/resources/lib/jquery/plugins/validate/jquery.validate.js"></script>
<script src="<%=basePath%>/resources/lib/jquery/plugins/validate/jquery.validate.messages_zh.js"></script>
<script src="<%=basePath%>/resources/lib/jquery/plugins/jquery.lazyload.js"></script>
--%>

<script src="http://cdn.bootcss.com/jquery/1.11.0/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

<script src="<%=basePath%>/resources/lib/jquery/plugins/jquery.md5.js"></script>
<script src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<script src="http://cdn.bootcss.com/jquery-validate/1.12.0/jquery.validate.min.js"></script>
<script src="http://cdn.bootcss.com/jquery-validate/1.12.0/jquery.validate.min.js"></script>
<script src="<%=basePath%>/resources/lib/jquery/plugins/validate/jquery.validate.messages_zh.js"></script>
<script src="http://cdn.bootcss.com/jquery.lazyload/1.9.1/jquery.lazyload.min.js"></script>

<script type="text/javascript">
	$(function() {
	    'use strict';

	    $.ajaxSetup({
	        cache : false,
	        dataType : 'json',
	        // contentType : 'application/json; charset=utf-8',
	        // contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
	        beforeSend : function(xhr) {
	        },
	        global : false,
	        error : function(err) {
		        console.log(err);
		        // window.location = document.location.href;
	        }
	    });

	    $('img.lazy').lazyload({
	        effect : 'fadeIn',
	        skip_invisible : false
	    });

	    $('.navbar-nav li a').removeClass('active');

	    // #######################################################
	    $.validator.addMethod('mobile', function(value, element, params) {
		    return this.optional(element) || /^0?(13|15|18|14|17)[0-9]{9}$/i.test(value);
	    }, $.validator.format('请输入正确的手机号码'));

	    $.validator.addMethod('zipcode', function(value, element, params) {
		    return this.optional(element) || /^\d{6}$/i.test(value);
	    }, $.validator.format('请输入正确的邮编'));
	    
	    $.validator.addMethod('username', function(value, element, params) {
			return this.optional(element) || /^[A-Za-z0-9_\-\u4e00-\u9fa5]+$/i.test(value);
		}, $.validator.format('请输入汉字、字母、数字及\"-\"、\"_\"组合'));
     	
	    $.validator.addMethod('notAllDigits', function(value, element, params) {
			return this.optional(element) || (/^[0-9]+$/i.test(value) == false);
		}, $.validator.format('不能是纯数字'));

	    $.validator.addMethod('complexPassword', function(value, element, params) {
		    var level = 0;

		    var pattern_1 = /^.*([\W_])+.*$/i;
		    var pattern_2 = /^.*([a-zA-Z])+.*$/i;
		    var pattern_3 = /^.*([0-9])+.*$/i;

		    if (value.length > 10) {
			    level++;
		    }
		    if (pattern_1.test(value)) {
			    level++;
		    }
		    if (pattern_2.test(value)) {
			    level++;
		    }
		    if (pattern_3.test(value)) {
			    level++;
		    }
		    if (level > 3) {
			    level = 3;
		    }

		    return this.optional(element) || level > 1;
	    }, $.validator.format('该密码比较简单，建议您更改为字母和数字的组合。'));

	    // #######################################################

	    $.fn.serializeObject = function(excludeFields) {
		    excludeFields = excludeFields || [];

		    var flag = 0;
		    var o = {};
		    var a = this.serializeArray();
		    $.each(a, function() {
			    var fieldName = this.name;
			    var fieldValue = this.value;

			    $.each(excludeFields, function(idx, item) {
				    if (fieldName == item) {
					    flag++;

					    return false;
				    }
			    });

			    if (flag == 0) {
				    if (o[fieldName] !== undefined) {
					    if (!o[fieldName].push) {
						    o[fieldName] = [ o[fieldName] ];
					    }

					    o[fieldName].push(fieldValue || '');
				    } else {
					    o[fieldName] = fieldValue || '';
				    }
			    }

			    flag = 0;
		    });

		    return o;
	    };
    });
</script>