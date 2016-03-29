<?php
/**
 * Inlis Worksheets (inlis-worksheets)
 * @var $this WorksheetController
 * @var $model SyncWorksheets
 * @var $form CActiveForm
 * version: 0.0.1
 *
 * @author Putra Sudaryanto <putra.sudaryanto@gmail.com>
 * @copyright Copyright (c) 2016 Ommu Platform (ommu.co)
 * @created date 29 March 2016, 10:00 WIB
 * @link http://company.ommu.co
 * @contect (+62)856-299-4114
 *
 */
?>

<?php $form=$this->beginWidget('CActiveForm', array(
	'action'=>Yii::app()->createUrl($this->route),
	'method'=>'get',
)); ?>
	<ul>
		<li>
			<?php echo $model->getAttributeLabel('ID'); ?><br/>
			<?php echo $form->textField($model,'ID'); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Name'); ?><br/>
			<?php echo $form->textField($model,'Name',array('size'=>60,'maxlength'=>100)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('CardFormat'); ?><br/>
			<?php echo $form->textField($model,'CardFormat',array('size'=>60,'maxlength'=>100)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Format_id'); ?><br/>
			<?php echo $form->textField($model,'Format_id'); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('CreateBy'); ?><br/>
			<?php echo $form->textField($model,'CreateBy',array('size'=>60,'maxlength'=>100)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('CreateDate'); ?><br/>
			<?php echo $form->textField($model,'CreateDate'); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('CreateTerminal'); ?><br/>
			<?php echo $form->textField($model,'CreateTerminal',array('size'=>60,'maxlength'=>100)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('UpdateBy'); ?><br/>
			<?php echo $form->textField($model,'UpdateBy',array('size'=>60,'maxlength'=>100)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('UpdateDate'); ?><br/>
			<?php echo $form->textField($model,'UpdateDate'); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('UpdateTerminal'); ?><br/>
			<?php echo $form->textField($model,'UpdateTerminal',array('size'=>60,'maxlength'=>100)); ?>
		</li>

		<li class="submit">
			<?php echo CHtml::submitButton(Yii::t('phrase', 'Search')); ?>
		</li>
	</ul>
<?php $this->endWidget(); ?>
