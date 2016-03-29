<?php
/**
 * Inlis Locations (inlis-locations)
 * @var $this LocationController
 * @var $model InlisLocations
 * version: 0.0.1
 *
 * @author Putra Sudaryanto <putra.sudaryanto@gmail.com>
 * @copyright Copyright (c) 2016 Ommu Platform (ommu.co)
 * @created date 29 March 2016, 11:07 WIB
 * @link http://company.ommu.co
 * @contect (+62)856-299-4114
 *
 */

	$this->breadcrumbs=array(
		'Inlis Locations'=>array('manage'),
		$model->Name,
	);
?>

<div class="dialog-content">
	<?php $this->widget('application.components.system.FDetailView', array(
		'data'=>$model,
		'attributes'=>array(
			array(
				'name'=>'ID',
				'value'=>$model->ID,
				//'value'=>$model->ID != '' ? $model->ID : '-',
			),
			array(
				'name'=>'Code',
				'value'=>$model->Code,
				//'value'=>$model->Code != '' ? $model->Code : '-',
			),
			array(
				'name'=>'Name',
				'value'=>$model->Name,
				//'value'=>$model->Name != '' ? $model->Name : '-',
			),
			array(
				'name'=>'Description',
				'value'=>$model->Description,
				//'value'=>$model->Description != '' ? $model->Description : '-',
			),
			array(
				'name'=>'IsDelete',
				'value'=>$model->IsDelete,
				//'value'=>$model->IsDelete != '' ? $model->IsDelete : '-',
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
		),
	)); ?>
</div>
<div class="dialog-submit">
	<?php echo CHtml::button(Yii::t('phrase', 'Close'), array('id'=>'closed')); ?>
</div>