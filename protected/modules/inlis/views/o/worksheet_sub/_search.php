<?php
/**
 * Inlis Worksheet Subs (inlis-worksheet-sub)
 * @var $this WorksheetsubController
 * @var $model InlisWorksheetSub
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
			<?php echo $form->textField($model,'Name',array('size'=>50,'maxlength'=>50)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Main_Worksheet_ID'); ?><br/>
			<?php echo $form->textField($model,'Main_Worksheet_ID'); ?>
		</li>

		<li class="submit">
			<?php echo CHtml::submitButton(Yii::t('phrase', 'Search')); ?>
		</li>
	</ul>
<?php $this->endWidget(); ?>
