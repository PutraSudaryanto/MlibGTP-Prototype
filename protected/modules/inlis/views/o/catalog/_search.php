<?php
/**
 * Inlis Catalogs (inlis-catalogs)
 * @var $this CatalogController
 * @var $model InlisCatalogs
 * @var $form CActiveForm
 * version: 0.0.1
 *
 * @author Putra Sudaryanto <putra.sudaryanto@gmail.com>
 * @copyright Copyright (c) 2016 Ommu Platform (ommu.co)
 * @created date 29 March 2016, 10:02 WIB
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
			<?php echo $model->getAttributeLabel('ControlNumber'); ?><br/>
			<?php echo $form->textField($model,'ControlNumber',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('BIBID'); ?><br/>
			<?php echo $form->textField($model,'BIBID',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Title'); ?><br/>
			<?php echo $form->textField($model,'Title',array('size'=>60,'maxlength'=>500)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Author'); ?><br/>
			<?php echo $form->textField($model,'Author',array('size'=>60,'maxlength'=>300)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Edition'); ?><br/>
			<?php echo $form->textArea($model,'Edition',array('rows'=>6, 'cols'=>50)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Publisher'); ?><br/>
			<?php echo $form->textField($model,'Publisher',array('size'=>60,'maxlength'=>300)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('PublishLocation'); ?><br/>
			<?php echo $form->textArea($model,'PublishLocation',array('rows'=>6, 'cols'=>50)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('PublishYear'); ?><br/>
			<?php echo $form->textArea($model,'PublishYear',array('rows'=>6, 'cols'=>50)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Paging'); ?><br/>
			<?php echo $form->textArea($model,'Paging',array('rows'=>6, 'cols'=>50)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Ill'); ?><br/>
			<?php echo $form->textArea($model,'Ill',array('rows'=>6, 'cols'=>50)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Sizes'); ?><br/>
			<?php echo $form->textArea($model,'Sizes',array('rows'=>6, 'cols'=>50)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Item'); ?><br/>
			<?php echo $form->textArea($model,'Item',array('rows'=>6, 'cols'=>50)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Subject'); ?><br/>
			<?php echo $form->textArea($model,'Subject',array('rows'=>6, 'cols'=>50)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Description'); ?><br/>
			<?php echo $form->textArea($model,'Description',array('rows'=>6, 'cols'=>50)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('ISBN'); ?><br/>
			<?php echo $form->textArea($model,'ISBN',array('rows'=>6, 'cols'=>50)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('CallNumber'); ?><br/>
			<?php echo $form->textArea($model,'CallNumber',array('rows'=>6, 'cols'=>50)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Note'); ?><br/>
			<?php echo $form->textArea($model,'Note',array('rows'=>6, 'cols'=>50)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Languages'); ?><br/>
			<?php echo $form->textArea($model,'Languages',array('rows'=>6, 'cols'=>50)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('DeweyNo'); ?><br/>
			<?php echo $form->textArea($model,'DeweyNo',array('rows'=>6, 'cols'=>50)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('ApproveDateOPAC'); ?><br/>
			<?php echo $form->textField($model,'ApproveDateOPAC'); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('IsOPAC'); ?><br/>
			<?php echo $form->textField($model,'IsOPAC'); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('IsBNI'); ?><br/>
			<?php echo $form->textField($model,'IsBNI'); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('IsKIN'); ?><br/>
			<?php echo $form->textField($model,'IsKIN'); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('IsDelete'); ?><br/>
			<?php echo $form->textField($model,'IsDelete'); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('CoverURL'); ?><br/>
			<?php echo $form->textField($model,'CoverURL',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('FileURL'); ?><br/>
			<?php echo $form->textField($model,'FileURL',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('MARC'); ?><br/>
			<?php echo $form->textArea($model,'MARC',array('rows'=>6, 'cols'=>50)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Branch_id'); ?><br/>
			<?php echo $form->textField($model,'Branch_id'); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Worksheet_id'); ?><br/>
			<?php echo $form->textField($model,'Worksheet_id'); ?>
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

		<li>
			<?php echo $model->getAttributeLabel('SLA_REGISTER'); ?><br/>
			<?php echo $form->textField($model,'SLA_REGISTER',array('size'=>45,'maxlength'=>45)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('SENAYAN_ID'); ?><br/>
			<?php echo $form->textField($model,'SENAYAN_ID',array('size'=>45,'maxlength'=>45)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('NCIBookMan_ID'); ?><br/>
			<?php echo $form->textField($model,'NCIBookMan_ID',array('size'=>45,'maxlength'=>45)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('collection_updated_count'); ?><br/>
			<?php echo $form->textField($model,'collection_updated_count'); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('tanggal'); ?><br/>
			<?php echo $form->textField($model,'tanggal'); ?>
		</li>

		<li class="submit">
			<?php echo CHtml::submitButton(Yii::t('phrase', 'Search')); ?>
		</li>
	</ul>
<?php $this->endWidget(); ?>
