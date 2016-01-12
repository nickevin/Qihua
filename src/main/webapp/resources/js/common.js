$.ajaxSetup({
    cache : false,
    dataType : 'json',
    // contentType : 'application/json; charset=utf-8',
	// contentType : 'application/x-www-form-urlencoded',
    global : false,
    beforeSend : function(xhr) {
	    // xhr.setRequestHeader('', '');
    },
    error : function(jqXHR, textStatus, errorThrown) {
	    console.log('ajaxSetup error: ' + textStatus);

	    // this.errorCallback(jqXHR, textStatus, errorThrown);
    },
    success : function(data, textStatus, jqXHR) {
	    if (data.result) {
		    this.successCallback(data, textStatus, jqXHR);
	    } else {
		    window.location = document.location.href;
	    }
    }
});

$('body').css({
    opacity : 1,
    'margin-left' : 0
});

// http://stackoverflow.com/questions/1184624/convert-form-data-to-js-object-with-jquery
// http://jsfiddle.net/sxGtM/3/
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