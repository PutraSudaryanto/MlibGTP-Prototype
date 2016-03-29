<?php
/**
 * Inlis Members (inlis-members)
 * @var $this MemberController
 * @var $model SyncMembers
 * version: 0.0.1
 *
 * @author Putra Sudaryanto <putra.sudaryanto@gmail.com>
 * @copyright Copyright (c) 2016 Ommu Platform (ommu.co)
 * @created date 29 March 2016, 10:07 WIB
 * @link http://company.ommu.co
 * @contect (+62)856-299-4114
 *
 */

	$this->breadcrumbs=array(
		'Inlis Members'=>array('manage'),
		$model->ID,
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
			'name'=>'MemberNo',
			'value'=>$model->MemberNo,
			//'value'=>$model->MemberNo != '' ? $model->MemberNo : '-',
		),
		array(
			'name'=>'Fullname',
			'value'=>$model->Fullname,
			//'value'=>$model->Fullname != '' ? $model->Fullname : '-',
		),
		array(
			'name'=>'PlaceOfBirth',
			'value'=>$model->PlaceOfBirth,
			//'value'=>$model->PlaceOfBirth != '' ? $model->PlaceOfBirth : '-',
		),
		array(
			'name'=>'DateOfBirth',
			'value'=>!in_array($model->DateOfBirth, array('0000-00-00 00:00:00','1970-01-01 00:00:00')) ? Utility::dateFormat($model->DateOfBirth, true) : '-',
		),
		array(
			'name'=>'Address',
			'value'=>$model->Address,
			//'value'=>$model->Address != '' ? $model->Address : '-',
		),
		array(
			'name'=>'AddressNow',
			'value'=>$model->AddressNow,
			//'value'=>$model->AddressNow != '' ? $model->AddressNow : '-',
		),
		array(
			'name'=>'Phone',
			'value'=>$model->Phone,
			//'value'=>$model->Phone != '' ? $model->Phone : '-',
		),
		array(
			'name'=>'InstitutionName',
			'value'=>$model->InstitutionName,
			//'value'=>$model->InstitutionName != '' ? $model->InstitutionName : '-',
		),
		array(
			'name'=>'InstitutionAddress',
			'value'=>$model->InstitutionAddress,
			//'value'=>$model->InstitutionAddress != '' ? $model->InstitutionAddress : '-',
		),
		array(
			'name'=>'InstitutionPhone',
			'value'=>$model->InstitutionPhone,
			//'value'=>$model->InstitutionPhone != '' ? $model->InstitutionPhone : '-',
		),
		array(
			'name'=>'IdentityType',
			'value'=>$model->IdentityType,
			//'value'=>$model->IdentityType != '' ? $model->IdentityType : '-',
		),
		array(
			'name'=>'IdentityNo',
			'value'=>$model->IdentityNo,
			//'value'=>$model->IdentityNo != '' ? $model->IdentityNo : '-',
		),
		array(
			'name'=>'EducationLevel',
			'value'=>$model->EducationLevel,
			//'value'=>$model->EducationLevel != '' ? $model->EducationLevel : '-',
		),
		array(
			'name'=>'Religion',
			'value'=>$model->Religion,
			//'value'=>$model->Religion != '' ? $model->Religion : '-',
		),
		array(
			'name'=>'Sex',
			'value'=>$model->Sex,
			//'value'=>$model->Sex != '' ? $model->Sex : '-',
		),
		array(
			'name'=>'MaritalStatus',
			'value'=>$model->MaritalStatus,
			//'value'=>$model->MaritalStatus != '' ? $model->MaritalStatus : '-',
		),
		array(
			'name'=>'JobName',
			'value'=>$model->JobName,
			//'value'=>$model->JobName != '' ? $model->JobName : '-',
		),
		array(
			'name'=>'RegisterDate',
			'value'=>!in_array($model->RegisterDate, array('0000-00-00 00:00:00','1970-01-01 00:00:00')) ? Utility::dateFormat($model->RegisterDate, true) : '-',
		),
		array(
			'name'=>'EndDate',
			'value'=>!in_array($model->EndDate, array('0000-00-00 00:00:00','1970-01-01 00:00:00')) ? Utility::dateFormat($model->EndDate, true) : '-',
		),
		array(
			'name'=>'BarCode',
			'value'=>$model->BarCode,
			//'value'=>$model->BarCode != '' ? $model->BarCode : '-',
		),
		array(
			'name'=>'PicPath',
			'value'=>$model->PicPath,
			//'value'=>$model->PicPath != '' ? $model->PicPath : '-',
		),
		array(
			'name'=>'MotherMaidenName',
			'value'=>$model->MotherMaidenName,
			//'value'=>$model->MotherMaidenName != '' ? $model->MotherMaidenName : '-',
		),
		array(
			'name'=>'Email',
			'value'=>$model->Email,
			//'value'=>$model->Email != '' ? $model->Email : '-',
		),
		array(
			'name'=>'JenisPermohonan',
			'value'=>$model->JenisPermohonan,
			//'value'=>$model->JenisPermohonan != '' ? $model->JenisPermohonan : '-',
		),
		array(
			'name'=>'JenisPermohonanName',
			'value'=>$model->JenisPermohonanName,
			//'value'=>$model->JenisPermohonanName != '' ? $model->JenisPermohonanName : '-',
		),
		array(
			'name'=>'JenisAnggota',
			'value'=>$model->JenisAnggota,
			//'value'=>$model->JenisAnggota != '' ? $model->JenisAnggota : '-',
		),
		array(
			'name'=>'JenisAnggotaName',
			'value'=>$model->JenisAnggotaName,
			//'value'=>$model->JenisAnggotaName != '' ? $model->JenisAnggotaName : '-',
		),
		array(
			'name'=>'StatusAnggota',
			'value'=>$model->StatusAnggota,
			//'value'=>$model->StatusAnggota != '' ? $model->StatusAnggota : '-',
		),
		array(
			'name'=>'StatusAnggotaName',
			'value'=>$model->StatusAnggotaName,
			//'value'=>$model->StatusAnggotaName != '' ? $model->StatusAnggotaName : '-',
		),
		array(
			'name'=>'Handphone',
			'value'=>$model->Handphone,
			//'value'=>$model->Handphone != '' ? $model->Handphone : '-',
		),
		array(
			'name'=>'ParentName',
			'value'=>$model->ParentName,
			//'value'=>$model->ParentName != '' ? $model->ParentName : '-',
		),
		array(
			'name'=>'ParentAddress',
			'value'=>$model->ParentAddress,
			//'value'=>$model->ParentAddress != '' ? $model->ParentAddress : '-',
		),
		array(
			'name'=>'ParentPhone',
			'value'=>$model->ParentPhone,
			//'value'=>$model->ParentPhone != '' ? $model->ParentPhone : '-',
		),
		array(
			'name'=>'ParentHandphone',
			'value'=>$model->ParentHandphone,
			//'value'=>$model->ParentHandphone != '' ? $model->ParentHandphone : '-',
		),
		array(
			'name'=>'Nationality',
			'value'=>$model->Nationality,
			//'value'=>$model->Nationality != '' ? $model->Nationality : '-',
		),
		array(
			'name'=>'LoanReturnLateCount',
			'value'=>$model->LoanReturnLateCount,
			//'value'=>$model->LoanReturnLateCount != '' ? $model->LoanReturnLateCount : '-',
		),
		array(
			'name'=>'Branch_id',
			'value'=>$model->Branch_id,
			//'value'=>$model->Branch_id != '' ? $model->Branch_id : '-',
		),
		array(
			'name'=>'User_id',
			'value'=>$model->User_id,
			//'value'=>$model->User_id != '' ? $model->User_id : '-',
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
			'name'=>'AlamatDomisili',
			'value'=>$model->AlamatDomisili,
			//'value'=>$model->AlamatDomisili != '' ? $model->AlamatDomisili : '-',
		),
		array(
			'name'=>'RT',
			'value'=>$model->RT,
			//'value'=>$model->RT != '' ? $model->RT : '-',
		),
		array(
			'name'=>'RW',
			'value'=>$model->RW,
			//'value'=>$model->RW != '' ? $model->RW : '-',
		),
		array(
			'name'=>'Kelurahan',
			'value'=>$model->Kelurahan,
			//'value'=>$model->Kelurahan != '' ? $model->Kelurahan : '-',
		),
		array(
			'name'=>'Kecamatan',
			'value'=>$model->Kecamatan,
			//'value'=>$model->Kecamatan != '' ? $model->Kecamatan : '-',
		),
		array(
			'name'=>'Kota',
			'value'=>$model->Kota,
			//'value'=>$model->Kota != '' ? $model->Kota : '-',
		),
		array(
			'name'=>'KodePos',
			'value'=>$model->KodePos,
			//'value'=>$model->KodePos != '' ? $model->KodePos : '-',
		),
		array(
			'name'=>'NoHp',
			'value'=>$model->NoHp,
			//'value'=>$model->NoHp != '' ? $model->NoHp : '-',
		),
		array(
			'name'=>'NamaDarurat',
			'value'=>$model->NamaDarurat,
			//'value'=>$model->NamaDarurat != '' ? $model->NamaDarurat : '-',
		),
		array(
			'name'=>'TelpDarurat',
			'value'=>$model->TelpDarurat,
			//'value'=>$model->TelpDarurat != '' ? $model->TelpDarurat : '-',
		),
		array(
			'name'=>'AlamatDarurat',
			'value'=>$model->AlamatDarurat,
			//'value'=>$model->AlamatDarurat != '' ? $model->AlamatDarurat : '-',
		),
		array(
			'name'=>'StatusHubunganDarurat',
			'value'=>$model->StatusHubunganDarurat,
			//'value'=>$model->StatusHubunganDarurat != '' ? $model->StatusHubunganDarurat : '-',
		),
		array(
			'name'=>'City',
			'value'=>$model->City,
			//'value'=>$model->City != '' ? $model->City : '-',
		),
		array(
			'name'=>'Province',
			'value'=>$model->Province,
			//'value'=>$model->Province != '' ? $model->Province : '-',
		),
		array(
			'name'=>'CityNow',
			'value'=>$model->CityNow,
			//'value'=>$model->CityNow != '' ? $model->CityNow : '-',
		),
		array(
			'name'=>'ProvinceNow',
			'value'=>$model->ProvinceNow,
			//'value'=>$model->ProvinceNow != '' ? $model->ProvinceNow : '-',
		),
		array(
			'name'=>'JobNameDetail',
			'value'=>$model->JobNameDetail,
			//'value'=>$model->JobNameDetail != '' ? $model->JobNameDetail : '-',
		),
	),
)); ?>