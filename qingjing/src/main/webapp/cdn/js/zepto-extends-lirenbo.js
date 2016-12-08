$.fn.serializeJSONString = function() {
	var name, type, result = new Object(),

	add = function(name, value) {
		result[name] = value;
	}

	if (this[0])
		$.each(this[0].elements, function(_, field) {
			type = field.type, name = field.name

			if (name && field.nodeName.toLowerCase() != 'fieldset' && !field.disabled && type != 'submit' && type != 'reset' && type != 'button' && type != 'file' &&

			((type != 'radio' && type != 'checkbox') || field.checked))

				add(name, $(field).val());
		})
	return JSON.stringify(result);
};

$.extend($, {
	postJSON : function(url, data, successFunction) {
		$.ajax({
			'type' : 'POST',
			'url' : url,
			'data' : data,
			'dataType' : 'json',
			'contentType' : 'application/json',
			'success' : successFunction
		});
		return 1;
	}
});
