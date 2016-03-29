<?php
/**
 * Inlis Catalogs (inlis-catalogs)
 * @var $this AdminController
 * @var $model InlisCatalogs
 * version: 0.0.1
 *
 * @author Putra Sudaryanto <putra.sudaryanto@gmail.com>
 * @copyright Copyright (c) 2016 Ommu Platform (ommu.co)
 * @created date 29 March 2016, 15:15 WIB
 * @link http://company.ommu.co
 * @contect (+62)856-299-4114
 *
 */

	$this->breadcrumbs=array(
		'Inlis Catalogs'=>array('manage'),
		$model->id,
	);
?>

<div class="dialog-content">
	<?php $this->widget('application.components.system.FDetailView', array(
		'data'=>$model,
		'attributes'=>array(
			array(
				'name'=>'id',
				'value'=>$model->id,
				//'value'=>$model->id != '' ? $model->id : '-',
			),
			array(
				'name'=>'catalog_id',
				'value'=>$model->catalog_id,
				//'value'=>$model->catalog_id != '' ? $model->catalog_id : '-',
			),
			array(
				'name'=>'creation_date',
				'value'=>!in_array($model->creation_date, array('0000-00-00 00:00:00','1970-01-01 00:00:00')) ? Utility::dateFormat($model->creation_date, true) : '-',
			),
			array(
				'name'=>'creation_id',
				'value'=>$model->creation_id,
				//'value'=>$model->creation_id != 0 ? $model->creation_id : '-',
			),
		),
	)); ?>
</div>
<div class="dialog-submit">
	<?php echo CHtml::button(Yii::t('phrase', 'Close'), array('id'=>'closed')); ?>
</div>
