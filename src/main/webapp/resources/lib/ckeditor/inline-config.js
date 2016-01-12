CKEDITOR.editorConfig = function(config) {
	config.language = 'zh-cn';

	config.toolbar = [
			/*
			 * { name : 'document', items : [ 'Source', '-', 'NewPage',
			 * 'Preview', '-', 'Templates' ] },
			 */
			{
				name : 'styles',
				items : [ // 'Format',
				'Font', 'FontSize' ]
			},
			{
				name : 'colors',
				items : [ 'TextColor', 'BGColor' ]
			},
			{
				name : 'editing',
				groups : [ 'find', 'selection', 'spellchecker' ],
				items : [ 'Find', 'Replace' ]
			},
			{
				name : 'basicstyles',
				groups : [ 'basicstyles', 'cleanup' ],
				items : [ 'Bold', 'Italic', 'Underline', 'Strike' ]
			},
			{
				name : 'links',
				items : [ 'Link', 'Unlink'// , 'Anchor'
				]
			},
			{
				name : 'insert',
				items : [ 'Image', 'Table', 'HorizontalRule' ]
			},
			// {
			// name : 'tools',
			// items : [ 'Maximize', 'ShowBlocks' ]
			// },
			// '/',
			{
				name : 'paragraph',
				groups : [ 'list', 'indent', 'blocks', 'align', 'bidi' ],
				items : [ 'NumberedList', 'BulletedList', '-', 'Outdent',
						'Indent', '-', 'Blockquote', '-', 'JustifyLeft',
						'JustifyCenter', 'JustifyRight', 'JustifyBlock' ]
			}, {
				name : 'document',
				groups : [ 'mode', 'document', 'doctools' ],
				items : [ 'Source'
				// , '-', 'Save', 'NewPage', 'Preview', 'Print', '-',
				// 'Templates'
				]
			} ];

	config.font_names = '宋体/宋体;黑体/黑体;仿宋/仿宋_GB2312;楷体/楷体_GB2312;微软雅黑/微软雅黑;'
			+ config.font_names;
};
