<?php
/**
 * Inlis Members (inlis-members)
 * @var $this MemberController
 * @var $model InlisMembers
 * @var $form CActiveForm
 * version: 0.0.1
 *
 * @author Putra Sudaryanto <putra.sudaryanto@gmail.com>
 * @copyright Copyright (c) 2016 Ommu Platform (ommu.co)
 * @created date 29 March 2016, 10:07 WIB
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
			<?php echo $model->getAttributeLabel('MemberNo'); ?><br/>
			<?php echo $form->textField($model,'MemberNo',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Fullname'); ?><br/>
			<?php echo $form->textField($model,'Fullname',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('PlaceOfBirth'); ?><br/>
			<?php echo $form->textField($model,'PlaceOfBirth',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('DateOfBirth'); ?><br/>
			<?php echo $form->textField($model,'DateOfBirth'); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Address'); ?><br/>
			<?php echo $form->textField($model,'Address',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('AddressNow'); ?><br/>
			<?php echo $form->textField($model,'AddressNow',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Phone'); ?><br/>
			<?php echo $form->textField($model,'Phone',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('InstitutionName'); ?><br/>
			<?php echo $form->textField($model,'InstitutionName',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('InstitutionAddress'); ?><br/>
			<?php echo $form->textField($model,'InstitutionAddress',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('InstitutionPhone'); ?><br/>
			<?php echo $form->textField($model,'InstitutionPhone',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('IdentityType'); ?><br/>
			<?php echo $form->textField($model,'IdentityType',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('IdentityNo'); ?><br/>
			<?php echo $form->textField($model,'IdentityNo',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('EducationLevel'); ?><br/>
			<?php echo $form->textField($model,'EducationLevel',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Religion'); ?><br/>
			<?php echo $form->textField($model,'Religion',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Sex'); ?><br/>
			<?php echo $form->textField($model,'Sex',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('MaritalStatus'); ?><br/>
			<?php echo $form->textField($model,'MaritalStatus',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('JobName'); ?><br/>
			<?php echo $form->textField($model,'JobName',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('RegisterDate'); ?><br/>
			<?php echo $form->textField($model,'RegisterDate'); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('EndDate'); ?><br/>
			<?php echo $form->textField($model,'EndDate'); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('BarCode'); ?><br/>
			<?php echo $form->textField($model,'BarCode',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('PicPath'); ?><br/>
			<?php echo $form->textField($model,'PicPath',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('MotherMaidenName'); ?><br/>
			<?php echo $form->textField($model,'MotherMaidenName',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Email'); ?><br/>
			<?php echo $form->textField($model,'Email',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('JenisPermohonan'); ?><br/>
			<?php echo $form->textField($model,'JenisPermohonan',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('JenisPermohonanName'); ?><br/>
			<?php echo $form->textField($model,'JenisPermohonanName',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('JenisAnggota'); ?><br/>
			<?php echo $form->textField($model,'JenisAnggota',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('JenisAnggotaName'); ?><br/>
			<?php echo $form->textField($model,'JenisAnggotaName',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('StatusAnggota'); ?><br/>
			<?php echo $form->textField($model,'StatusAnggota',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('StatusAnggotaName'); ?><br/>
			<?php echo $form->textField($model,'StatusAnggotaName',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Handphone'); ?><br/>
			<?php echo $form->textField($model,'Handphone',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('ParentName'); ?><br/>
			<?php echo $form->textField($model,'ParentName',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('ParentAddress'); ?><br/>
			<?php echo $form->textField($model,'ParentAddress',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('ParentPhone'); ?><br/>
			<?php echo $form->textField($model,'ParentPhone',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('ParentHandphone'); ?><br/>
			<?php echo $form->textField($model,'ParentHandphone',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Nationality'); ?><br/>
			<?php echo $form->textField($model,'Nationality',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('LoanReturnLateCount'); ?><br/>
			<?php echo $form->textField($model,'LoanReturnLateCount'); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Branch_id'); ?><br/>
			<?php echo $form->textField($model,'Branch_id'); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('User_id'); ?><br/>
			<?php echo $form->textField($model,'User_id'); ?>
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
			<?php echo $model->getAttributeLabel('AlamatDomisili'); ?><br/>
			<?php echo $form->textField($model,'AlamatDomisili',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('RT'); ?><br/>
			<?php echo $form->textField($model,'RT',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('RW'); ?><br/>
			<?php echo $form->textField($model,'RW',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Kelurahan'); ?><br/>
			<?php echo $form->textField($model,'Kelurahan',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Kecamatan'); ?><br/>
			<?php echo $form->textField($model,'Kecamatan',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Kota'); ?><br/>
			<?php echo $form->textField($model,'Kota',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('KodePos'); ?><br/>
			<?php echo $form->textField($model,'KodePos',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('NoHp'); ?><br/>
			<?php echo $form->textField($model,'NoHp',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('NamaDarurat'); ?><br/>
			<?php echo $form->textField($model,'NamaDarurat',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('TelpDarurat'); ?><br/>
			<?php echo $form->textField($model,'TelpDarurat',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('AlamatDarurat'); ?><br/>
			<?php echo $form->textField($model,'AlamatDarurat',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('StatusHubunganDarurat'); ?><br/>
			<?php echo $form->textField($model,'StatusHubunganDarurat',array('size'=>60,'maxlength'=>255)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('City'); ?><br/>
			<?php echo $form->textField($model,'City',array('size'=>45,'maxlength'=>45)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('Province'); ?><br/>
			<?php echo $form->textField($model,'Province',array('size'=>45,'maxlength'=>45)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('CityNow'); ?><br/>
			<?php echo $form->textField($model,'CityNow',array('size'=>45,'maxlength'=>45)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('ProvinceNow'); ?><br/>
			<?php echo $form->textField($model,'ProvinceNow',array('size'=>45,'maxlength'=>45)); ?>
		</li>

		<li>
			<?php echo $model->getAttributeLabel('JobNameDetail'); ?><br/>
			<?php echo $form->textField($model,'JobNameDetail',array('size'=>50,'maxlength'=>50)); ?>
		</li>

		<li class="submit">
			<?php echo CHtml::submitButton(Yii::t('phrase', 'Search')); ?>
		</li>
	</ul>
<?php $this->endWidget(); ?>
