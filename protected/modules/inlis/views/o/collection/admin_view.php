<?php
/**
 * Inlis Collections (inlis-collections)
 * @var $this CollectionController
 * @var $model SyncCollections
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
		'Inlis Collections'=>array('manage'),
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
			'name'=>'NoInduk',
			'value'=>$model->NoInduk,
			//'value'=>$model->NoInduk != '' ? $model->NoInduk : '-',
		),
		array(
			'name'=>'Title',
			'value'=>$model->Title != '' ? $model->Title : '-',
			//'value'=>$model->Title != '' ? CHtml::link($model->Title, Yii::app()->request->baseUrl.'/public/visit/'.$model->Title, array('target' => '_blank')) : '-',
			'type'=>'raw',
		),
		array(
			'name'=>'TitleAdded',
			'value'=>$model->TitleAdded != '' ? $model->TitleAdded : '-',
			//'value'=>$model->TitleAdded != '' ? CHtml::link($model->TitleAdded, Yii::app()->request->baseUrl.'/public/visit/'.$model->TitleAdded, array('target' => '_blank')) : '-',
			'type'=>'raw',
		),
		array(
			'name'=>'Author',
			'value'=>$model->Author != '' ? $model->Author : '-',
			//'value'=>$model->Author != '' ? CHtml::link($model->Author, Yii::app()->request->baseUrl.'/public/visit/'.$model->Author, array('target' => '_blank')) : '-',
			'type'=>'raw',
		),
		array(
			'name'=>'AuthorAdded',
			'value'=>$model->AuthorAdded != '' ? $model->AuthorAdded : '-',
			//'value'=>$model->AuthorAdded != '' ? CHtml::link($model->AuthorAdded, Yii::app()->request->baseUrl.'/public/visit/'.$model->AuthorAdded, array('target' => '_blank')) : '-',
			'type'=>'raw',
		),
		array(
			'name'=>'Cooperation',
			'value'=>$model->Cooperation,
			//'value'=>$model->Cooperation != '' ? $model->Cooperation : '-',
		),
		array(
			'name'=>'PublishLocation',
			'value'=>$model->PublishLocation,
			//'value'=>$model->PublishLocation != '' ? $model->PublishLocation : '-',
		),
		array(
			'name'=>'Publisher',
			'value'=>$model->Publisher != '' ? $model->Publisher : '-',
			//'value'=>$model->Publisher != '' ? CHtml::link($model->Publisher, Yii::app()->request->baseUrl.'/public/visit/'.$model->Publisher, array('target' => '_blank')) : '-',
			'type'=>'raw',
		),
		array(
			'name'=>'PublishYear',
			'value'=>$model->PublishYear,
			//'value'=>$model->PublishYear != '' ? $model->PublishYear : '-',
		),
		array(
			'name'=>'KalaTerbit',
			'value'=>$model->KalaTerbit,
			//'value'=>$model->KalaTerbit != '' ? $model->KalaTerbit : '-',
		),
		array(
			'name'=>'Edition',
			'value'=>$model->Edition,
			//'value'=>$model->Edition != '' ? $model->Edition : '-',
		),
		array(
			'name'=>'Class',
			'value'=>$model->Class,
			//'value'=>$model->Class != '' ? $model->Class : '-',
		),
		array(
			'name'=>'PhysicalDescription',
			'value'=>$model->PhysicalDescription != '' ? $model->PhysicalDescription : '-',
			//'value'=>$model->PhysicalDescription != '' ? CHtml::link($model->PhysicalDescription, Yii::app()->request->baseUrl.'/public/visit/'.$model->PhysicalDescription, array('target' => '_blank')) : '-',
			'type'=>'raw',
		),
		array(
			'name'=>'Note',
			'value'=>$model->Note != '' ? $model->Note : '-',
			//'value'=>$model->Note != '' ? CHtml::link($model->Note, Yii::app()->request->baseUrl.'/public/visit/'.$model->Note, array('target' => '_blank')) : '-',
			'type'=>'raw',
		),
		array(
			'name'=>'Currency',
			'value'=>$model->Currency,
			//'value'=>$model->Currency != '' ? $model->Currency : '-',
		),
		array(
			'name'=>'ISBN',
			'value'=>$model->ISBN,
			//'value'=>$model->ISBN != '' ? $model->ISBN : '-',
		),
		array(
			'name'=>'MapScale',
			'value'=>$model->MapScale,
			//'value'=>$model->MapScale != '' ? $model->MapScale : '-',
		),
		array(
			'name'=>'MapNumber',
			'value'=>$model->MapNumber,
			//'value'=>$model->MapNumber != '' ? $model->MapNumber : '-',
		),
		array(
			'name'=>'MapSubject',
			'value'=>$model->MapSubject,
			//'value'=>$model->MapSubject != '' ? $model->MapSubject : '-',
		),
		array(
			'name'=>'RFID',
			'value'=>$model->RFID,
			//'value'=>$model->RFID != '' ? $model->RFID : '-',
		),
		array(
			'name'=>'Price',
			'value'=>$model->Price,
			//'value'=>$model->Price != '' ? $model->Price : '-',
		),
		array(
			'name'=>'TanggalKirim',
			'value'=>!in_array($model->TanggalKirim, array('0000-00-00 00:00:00','1970-01-01 00:00:00')) ? Utility::dateFormat($model->TanggalKirim, true) : '-',
		),
		array(
			'name'=>'IsDelete',
			'value'=>$model->IsDelete,
			//'value'=>$model->IsDelete != '' ? $model->IsDelete : '-',
		),
		array(
			'name'=>'Branch_id',
			'value'=>$model->Branch_id,
			//'value'=>$model->Branch_id != '' ? $model->Branch_id : '-',
		),
		array(
			'name'=>'Catalog_id',
			'value'=>$model->Catalog_id,
			//'value'=>$model->Catalog_id != '' ? $model->Catalog_id : '-',
		),
		array(
			'name'=>'Partner_id',
			'value'=>$model->Partner_id,
			//'value'=>$model->Partner_id != '' ? $model->Partner_id : '-',
		),
		array(
			'name'=>'Location_id',
			'value'=>$model->Location_id,
			//'value'=>$model->Location_id != '' ? $model->Location_id : '-',
		),
		array(
			'name'=>'Rule_id',
			'value'=>$model->Rule_id,
			//'value'=>$model->Rule_id != '' ? $model->Rule_id : '-',
		),
		array(
			'name'=>'Category_id',
			'value'=>$model->Category_id,
			//'value'=>$model->Category_id != '' ? $model->Category_id : '-',
		),
		array(
			'name'=>'Media_id',
			'value'=>$model->Media_id,
			//'value'=>$model->Media_id != '' ? $model->Media_id : '-',
		),
		array(
			'name'=>'Source_id',
			'value'=>$model->Source_id,
			//'value'=>$model->Source_id != '' ? $model->Source_id : '-',
		),
		array(
			'name'=>'Worksheet_id',
			'value'=>$model->Worksheet_id,
			//'value'=>$model->Worksheet_id != '' ? $model->Worksheet_id : '-',
		),
		array(
			'name'=>'GroupingNumber',
			'value'=>$model->GroupingNumber,
			//'value'=>$model->GroupingNumber != '' ? $model->GroupingNumber : '-',
		),
		array(
			'name'=>'NomorBarcode',
			'value'=>$model->NomorBarcode,
			//'value'=>$model->NomorBarcode != '' ? $model->NomorBarcode : '-',
		),
		array(
			'name'=>'Status',
			'value'=>$model->Status,
			//'value'=>$model->Status != '' ? $model->Status : '-',
		),
		array(
			'name'=>'Keterangan_Sumber',
			'value'=>$model->Keterangan_Sumber,
			//'value'=>$model->Keterangan_Sumber != '' ? $model->Keterangan_Sumber : '-',
		),
		array(
			'name'=>'Penempatan',
			'value'=>$model->Penempatan,
			//'value'=>$model->Penempatan != '' ? $model->Penempatan : '-',
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
			'name'=>'IsVerified',
			'value'=>$model->IsVerified,
			//'value'=>$model->IsVerified != '' ? $model->IsVerified : '-',
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
	),
)); ?>