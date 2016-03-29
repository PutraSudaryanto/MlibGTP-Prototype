<?php
/**
 * Inlis Catalogs (inlis-catalogs)
 * @var $this CatalogController
 * @var $model SyncCatalogs
 * version: 0.0.1
 *
 * @author Putra Sudaryanto <putra.sudaryanto@gmail.com>
 * @copyright Copyright (c) 2016 Ommu Platform (ommu.co)
 * @created date 29 March 2016, 10:02 WIB
 * @link http://company.ommu.co
 * @contect (+62)856-299-4114
 *
 */

	$this->breadcrumbs=array(
		'Inlis Catalogs'=>array('manage'),
		$model->Title,
	);
?>

<?php $this->widget('application.components.system.FDetailView', array(
	'data'=>$model,
	'attributes'=>array(
		array(
			'name'=>'ID',
			'value'=>$model->ID,
			//'value'=>$model->ID != '' ? $model->ID : '-',
		),
		array(
			'name'=>'ControlNumber',
			'value'=>$model->ControlNumber,
			//'value'=>$model->ControlNumber != '' ? $model->ControlNumber : '-',
		),
		array(
			'name'=>'BIBID',
			'value'=>$model->BIBID,
			//'value'=>$model->BIBID != '' ? $model->BIBID : '-',
		),
		array(
			'name'=>'Title',
			'value'=>$model->Title,
			//'value'=>$model->Title != '' ? $model->Title : '-',
		),
		array(
			'name'=>'Author',
			'value'=>$model->Author,
			//'value'=>$model->Author != '' ? $model->Author : '-',
		),
		array(
			'name'=>'Edition',
			'value'=>$model->Edition != '' ? $model->Edition : '-',
			//'value'=>$model->Edition != '' ? CHtml::link($model->Edition, Yii::app()->request->baseUrl.'/public/visit/'.$model->Edition, array('target' => '_blank')) : '-',
			'type'=>'raw',
		),
		array(
			'name'=>'Publisher',
			'value'=>$model->Publisher,
			//'value'=>$model->Publisher != '' ? $model->Publisher : '-',
		),
		array(
			'name'=>'PublishLocation',
			'value'=>$model->PublishLocation != '' ? $model->PublishLocation : '-',
			//'value'=>$model->PublishLocation != '' ? CHtml::link($model->PublishLocation, Yii::app()->request->baseUrl.'/public/visit/'.$model->PublishLocation, array('target' => '_blank')) : '-',
			'type'=>'raw',
		),
		array(
			'name'=>'PublishYear',
			'value'=>$model->PublishYear != '' ? $model->PublishYear : '-',
			//'value'=>$model->PublishYear != '' ? CHtml::link($model->PublishYear, Yii::app()->request->baseUrl.'/public/visit/'.$model->PublishYear, array('target' => '_blank')) : '-',
			'type'=>'raw',
		),
		array(
			'name'=>'Paging',
			'value'=>$model->Paging != '' ? $model->Paging : '-',
			//'value'=>$model->Paging != '' ? CHtml::link($model->Paging, Yii::app()->request->baseUrl.'/public/visit/'.$model->Paging, array('target' => '_blank')) : '-',
			'type'=>'raw',
		),
		array(
			'name'=>'Ill',
			'value'=>$model->Ill != '' ? $model->Ill : '-',
			//'value'=>$model->Ill != '' ? CHtml::link($model->Ill, Yii::app()->request->baseUrl.'/public/visit/'.$model->Ill, array('target' => '_blank')) : '-',
			'type'=>'raw',
		),
		array(
			'name'=>'Sizes',
			'value'=>$model->Sizes != '' ? $model->Sizes : '-',
			//'value'=>$model->Sizes != '' ? CHtml::link($model->Sizes, Yii::app()->request->baseUrl.'/public/visit/'.$model->Sizes, array('target' => '_blank')) : '-',
			'type'=>'raw',
		),
		array(
			'name'=>'Item',
			'value'=>$model->Item != '' ? $model->Item : '-',
			//'value'=>$model->Item != '' ? CHtml::link($model->Item, Yii::app()->request->baseUrl.'/public/visit/'.$model->Item, array('target' => '_blank')) : '-',
			'type'=>'raw',
		),
		array(
			'name'=>'Subject',
			'value'=>$model->Subject != '' ? $model->Subject : '-',
			//'value'=>$model->Subject != '' ? CHtml::link($model->Subject, Yii::app()->request->baseUrl.'/public/visit/'.$model->Subject, array('target' => '_blank')) : '-',
			'type'=>'raw',
		),
		array(
			'name'=>'Description',
			'value'=>$model->Description != '' ? $model->Description : '-',
			//'value'=>$model->Description != '' ? CHtml::link($model->Description, Yii::app()->request->baseUrl.'/public/visit/'.$model->Description, array('target' => '_blank')) : '-',
			'type'=>'raw',
		),
		array(
			'name'=>'ISBN',
			'value'=>$model->ISBN != '' ? $model->ISBN : '-',
			//'value'=>$model->ISBN != '' ? CHtml::link($model->ISBN, Yii::app()->request->baseUrl.'/public/visit/'.$model->ISBN, array('target' => '_blank')) : '-',
			'type'=>'raw',
		),
		array(
			'name'=>'CallNumber',
			'value'=>$model->CallNumber != '' ? $model->CallNumber : '-',
			//'value'=>$model->CallNumber != '' ? CHtml::link($model->CallNumber, Yii::app()->request->baseUrl.'/public/visit/'.$model->CallNumber, array('target' => '_blank')) : '-',
			'type'=>'raw',
		),
		array(
			'name'=>'Note',
			'value'=>$model->Note != '' ? $model->Note : '-',
			//'value'=>$model->Note != '' ? CHtml::link($model->Note, Yii::app()->request->baseUrl.'/public/visit/'.$model->Note, array('target' => '_blank')) : '-',
			'type'=>'raw',
		),
		array(
			'name'=>'Languages',
			'value'=>$model->Languages != '' ? $model->Languages : '-',
			//'value'=>$model->Languages != '' ? CHtml::link($model->Languages, Yii::app()->request->baseUrl.'/public/visit/'.$model->Languages, array('target' => '_blank')) : '-',
			'type'=>'raw',
		),
		array(
			'name'=>'DeweyNo',
			'value'=>$model->DeweyNo != '' ? $model->DeweyNo : '-',
			//'value'=>$model->DeweyNo != '' ? CHtml::link($model->DeweyNo, Yii::app()->request->baseUrl.'/public/visit/'.$model->DeweyNo, array('target' => '_blank')) : '-',
			'type'=>'raw',
		),
		array(
			'name'=>'ApproveDateOPAC',
			'value'=>!in_array($model->ApproveDateOPAC, array('0000-00-00 00:00:00','1970-01-01 00:00:00')) ? Utility::dateFormat($model->ApproveDateOPAC, true) : '-',
		),
		array(
			'name'=>'IsOPAC',
			'value'=>$model->IsOPAC,
			//'value'=>$model->IsOPAC != '' ? $model->IsOPAC : '-',
		),
		array(
			'name'=>'IsBNI',
			'value'=>$model->IsBNI,
			//'value'=>$model->IsBNI != '' ? $model->IsBNI : '-',
		),
		array(
			'name'=>'IsKIN',
			'value'=>$model->IsKIN,
			//'value'=>$model->IsKIN != '' ? $model->IsKIN : '-',
		),
		array(
			'name'=>'IsDelete',
			'value'=>$model->IsDelete,
			//'value'=>$model->IsDelete != '' ? $model->IsDelete : '-',
		),
		array(
			'name'=>'CoverURL',
			'value'=>$model->CoverURL,
			//'value'=>$model->CoverURL != '' ? $model->CoverURL : '-',
		),
		array(
			'name'=>'FileURL',
			'value'=>$model->FileURL,
			//'value'=>$model->FileURL != '' ? $model->FileURL : '-',
		),
		array(
			'name'=>'MARC',
			'value'=>$model->MARC != '' ? $model->MARC : '-',
			//'value'=>$model->MARC != '' ? CHtml::link($model->MARC, Yii::app()->request->baseUrl.'/public/visit/'.$model->MARC, array('target' => '_blank')) : '-',
			'type'=>'raw',
		),
		array(
			'name'=>'Branch_id',
			'value'=>$model->Branch_id,
			//'value'=>$model->Branch_id != '' ? $model->Branch_id : '-',
		),
		array(
			'name'=>'Worksheet_id',
			'value'=>$model->Worksheet_id,
			//'value'=>$model->Worksheet_id != '' ? $model->Worksheet_id : '-',
		),
		array(
			'name'=>'CreateBy',
			'value'=>$model->CreateBy,
			//'value'=>$model->CreateBy != '' ? $model->CreateBy : '-',
		),
		array(
			'name'=>'CreateDate',
			'value'=>!in_array($model->CreateDate, array('0000-00-00 00:00:00','1970-01-01 00:00:00')) ? Utility::dateFormat($model->CreateDate, true) : '-',
		),
		array(
			'name'=>'CreateTerminal',
			'value'=>$model->CreateTerminal,
			//'value'=>$model->CreateTerminal != '' ? $model->CreateTerminal : '-',
		),
		array(
			'name'=>'UpdateBy',
			'value'=>$model->UpdateBy,
			//'value'=>$model->UpdateBy != '' ? $model->UpdateBy : '-',
		),
		array(
			'name'=>'UpdateDate',
			'value'=>!in_array($model->UpdateDate, array('0000-00-00 00:00:00','1970-01-01 00:00:00')) ? Utility::dateFormat($model->UpdateDate, true) : '-',
		),
		array(
			'name'=>'UpdateTerminal',
			'value'=>$model->UpdateTerminal,
			//'value'=>$model->UpdateTerminal != '' ? $model->UpdateTerminal : '-',
		),
		array(
			'name'=>'SLA_REGISTER',
			'value'=>$model->SLA_REGISTER,
			//'value'=>$model->SLA_REGISTER != '' ? $model->SLA_REGISTER : '-',
		),
		array(
			'name'=>'SENAYAN_ID',
			'value'=>$model->SENAYAN_ID,
			//'value'=>$model->SENAYAN_ID != '' ? $model->SENAYAN_ID : '-',
		),
		array(
			'name'=>'NCIBookMan_ID',
			'value'=>$model->NCIBookMan_ID,
			//'value'=>$model->NCIBookMan_ID != '' ? $model->NCIBookMan_ID : '-',
		),
		array(
			'name'=>'collection_updated_count',
			'value'=>$model->collection_updated_count,
			//'value'=>$model->collection_updated_count != '' ? $model->collection_updated_count : '-',
		),
		array(
			'name'=>'tanggal',
			'value'=>!in_array($model->tanggal, array('0000-00-00 00:00:00','1970-01-01 00:00:00')) ? Utility::dateFormat($model->tanggal, true) : '-',
		),
	),
)); ?>