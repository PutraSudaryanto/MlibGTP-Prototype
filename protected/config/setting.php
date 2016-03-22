<?php
return array(
	// application-level parameters that can be accessed
	// using Yii::app()->params['paramName']
	'params'=>array(
		// this is used in contact page
		'adminEmail'=>'webmaster@example.com',
		'primaryLang' => 'id',
		'translateLangs' => array(
			'en' => 'en',
			'id' => 'id',
		),
		
		// timthumb replace url
		'timthumb_url_replace' => 0,		
		'timthumb_url_replace_website' => 'http://ommu.co',	//default http
		// access system *from product
		'product_access_system' => 'opac.bpadjogja.info',
		'oauth_server_options' => array(
			//'http://ommu.co',
			'http://localhost/_product_ommu.co',
		),
	),
);
?>