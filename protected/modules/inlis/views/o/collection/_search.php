<?php
/**
 * Inlis Collections (inlis-collections)
 * @var $this CollectionController
 * @var $model InlisCollections
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
			<?php echo $model->getAttributeLabel('NoInduk'); ?><br/>
			<?php echo $form->textField($model,'NoInduk',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Title'); ?><br/>
			<?php echo $form->textArea($model,'Title',array('rows'=>6, 'cols'=>50)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('TitleAdded'); ?><br/>
			<?php echo $form->textArea($model,'TitleAdded',array('rows'=>6, 'cols'=>50)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Author'); ?><br/>
			<?php echo $form->textArea($model,'Author',array('rows'=>6, 'cols'=>50)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('AuthorAdded'); ?><br/>
			<?php echo $form->textArea($model,'AuthorAdded',array('rows'=>6, 'cols'=>50)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Cooperation'); ?><br/>
			<?php echo $form->textField($model,'Cooperation',array('size'=>60,'maxlength'=>200)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('PublishLocation'); ?><br/>
			<?php echo $form->textField($model,'PublishLocation',array('size'=>60,'maxlength'=>200)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Publisher'); ?><br/>
			<?php echo $form->textArea($model,'Publisher',array('rows'=>6, 'cols'=>50)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('PublishYear'); ?><br/>
			<?php echo $form->textField($model,'PublishYear',array('size'=>60,'maxlength'=>200)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('KalaTerbit'); ?><br/>
			<?php echo $form->textField($model,'KalaTerbit',array('size'=>60,'maxlength'=>200)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Edition'); ?><br/>
			<?php echo $form->textField($model,'Edition',array('size'=>60,'maxlength'=>200)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Class'); ?><br/>
			<?php echo $form->textField($model,'Class',array('size'=>60,'maxlength'=>200)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('PhysicalDescription'); ?><br/>
			<?php echo $form->textArea($model,'PhysicalDescription',array('rows'=>6, 'cols'=>50)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Note'); ?><br/>
			<?php echo $form->textArea($model,'Note',array('rows'=>6, 'cols'=>50)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Currency'); ?><br/>
			<?php echo $form->textField($model,'Currency',array('size'=>30,'maxlength'=>30)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('ISBN'); ?><br/>
			<?php echo $form->textField($model,'ISBN',array('size'=>60,'maxlength'=>2000)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('MapScale'); ?><br/>
			<?php echo $form->textField($model,'MapScale',array('size'=>60,'maxlength'=>200)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('MapNumber'); ?><br/>
			<?php echo $form->textField($model,'MapNumber',array('size'=>60,'maxlength'=>200)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('MapSubject'); ?><br/>
			<?php echo $form->textField($model,'MapSubject',array('size'=>60,'maxlength'=>200)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('RFID'); ?><br/>
			<?php echo $form->textField($model,'RFID',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Price'); ?><br/>
			<?php echo $form->textField($model,'Price'); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('TanggalKirim'); ?><br/>
			<?php echo $form->textField($model,'TanggalKirim'); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('IsDelete'); ?><br/>
			<?php echo $form->textField($model,'IsDelete'); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Branch_id'); ?><br/>
			<?php echo $form->textField($model,'Branch_id'); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Catalog_id'); ?><br/>
			<?php echo $form->textField($model,'Catalog_id'); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Partner_id'); ?><br/>
			<?php echo $form->textField($model,'Partner_id'); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Location_id'); ?><br/>
			<?php echo $form->textField($model,'Location_id'); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Rule_id'); ?><br/>
			<?php echo $form->textField($model,'Rule_id'); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Category_id'); ?><br/>
			<?php echo $form->textField($model,'Category_id'); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Media_id'); ?><br/>
			<?php echo $form->textField($model,'Media_id'); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Source_id'); ?><br/>
			<?php echo $form->textField($model,'Source_id'); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Worksheet_id'); ?><br/>
			<?php echo $form->textField($model,'Worksheet_id'); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('GroupingNumber'); ?><br/>
			<?php echo $form->textField($model,'GroupingNumber'); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('NomorBarcode'); ?><br/>
			<?php echo $form->textField($model,'NomorBarcode',array('size'=>50,'maxlength'=>50)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Status'); ?><br/>
			<?php echo $form->textField($model,'Status',array('size'=>50,'maxlength'=>50)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Keterangan_Sumber'); ?><br/>
			<?php echo $form->textField($model,'Keterangan_Sumber',array('size'=>60,'maxlength'=>200)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Penempatan'); ?><br/>
			<?php echo $form->textField($model,'Penempatan',array('size'=>60,'maxlength'=>200)); ?>
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
			<?php echo $model->getAttributeLabel('IsVerified'); ?><br/>
			<?php echo $form->textField($model,'IsVerified'); ?>
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

		<li class="submit">
			<?php echo CHtml::submitButton(Yii::t('phrase', 'Search')); ?>
		</li>
	</ul>
<?php $this->endWidget(); ?>
