/**
 * @license Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights
 *          reserved. For licensing, see LICENSE.html or
 *          http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function(config) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';

	config.language = 'zh-cn';

	config.toolbar = [
			{
				name : 'styles',
				items : [ // 'Styles', 'Format',
				'Font', 'FontSize' ]
			},
			{
				name : 'colors',
				items : [ 'TextColor', 'BGColor' ]
			},
			// { name: 'clipboard', groups: [ 'clipboard', 'undo' ], items: [
			// 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-',
			// 'Undo', 'Redo' ] },
			/*
			 * { name : 'editing', groups : [ 'find', 'selection',
			 * 'spellchecker' ], items : [ 'Find', 'Replace' // , '-',
			 * 'SelectAll', '-', 'SpellChecker' ] },
			 */
			// { name: 'forms', items: [ 'Form', 'Checkbox', 'Radio',
			// 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton',
			// 'HiddenField' ] },
			{
				name : 'basicstyles',
				groups : [ 'basicstyles', 'cleanup' ],
				items : [ 'Bold', 'Italic', 'Underline', 'Strike'
				// , 'Subscript', 'Superscript'
				// , '-', 'RemoveFormat'
				]
			},
			{
				name : 'paragraph',
				groups : [ 'list', 'indent', 'blocks', 'align', 'bidi' ],
				items : [ 'NumberedList', 'BulletedList', '-', 'Outdent',
						'Indent', '-', 'Blockquote'
						// , 'CreateDiv'
						, '-', 'JustifyLeft', 'JustifyCenter', 'JustifyRight',
						'JustifyBlock'
				// , '-', 'BidiLtr', 'BidiRtl'
				// , 'Language'
				]
			},
			// {
			// name : 'links',
			// items : [ 'Link', 'Unlink', 'Anchor' ]
			//			}, 
			{
				name : 'insert',
				items : [ 'Image', 'Table', 'HorizontalRule'
				// 'Flash', 'Smiley', 'SpecialChar', 'PageBreak', 'Iframe'
				]
			},
			// {
			// name : 'tools',
			// items : [ 'Maximize', 'ShowBlocks' ]
			// },

			{
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
