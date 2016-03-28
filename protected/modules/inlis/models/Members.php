<?php
/**
 * Members
 * @author Putra Sudaryanto <putra.sudaryanto@gmail.com>
 * @copyright Copyright (c) 2016 Ommu Platform (ommu.co)
 * @created date 28 March 2016, 13:24 WIB
 * @link http://company.ommu.co
 * @contact (+62)856-299-4114
 *
 * This is the template for generating the model class of a specified table.
 * - $this: the ModelCode object
 * - $tableName: the table name for this class (prefix is already removed if necessary)
 * - $modelClass: the model class name
 * - $columns: list of table columns (name=>CDbColumnSchema)
 * - $labels: list of attribute labels (name=>label)
 * - $rules: list of validation rules
 * - $relations: list of relations (name=>relation declaration)
 *
 * --------------------------------------------------------------------------------------
 *
 * This is the model class for table "members".
 *
 * The followings are the available columns in table 'members':
 * @property double $ID
 * @property string $MemberNo
 * @property string $Fullname
 * @property string $PlaceOfBirth
 * @property string $DateOfBirth
 * @property string $Address
 * @property string $AddressNow
 * @property string $Phone
 * @property string $InstitutionName
 * @property string $InstitutionAddress
 * @property string $InstitutionPhone
 * @property string $IdentityType
 * @property string $IdentityNo
 * @property string $EducationLevel
 * @property string $Religion
 * @property string $Sex
 * @property string $MaritalStatus
 * @property string $JobName
 * @property string $RegisterDate
 * @property string $EndDate
 * @property string $BarCode
 * @property string $PicPath
 * @property string $MotherMaidenName
 * @property string $Email
 * @property string $JenisPermohonan
 * @property string $JenisPermohonanName
 * @property string $JenisAnggota
 * @property string $JenisAnggotaName
 * @property string $StatusAnggota
 * @property string $StatusAnggotaName
 * @property string $Handphone
 * @property string $ParentName
 * @property string $ParentAddress
 * @property string $ParentPhone
 * @property string $ParentHandphone
 * @property string $Nationality
 * @property integer $LoanReturnLateCount
 * @property integer $Branch_id
 * @property integer $User_id
 * @property string $CreateBy
 * @property string $CreateDate
 * @property string $CreateTerminal
 * @property string $UpdateBy
 * @property string $UpdateDate
 * @property string $UpdateTerminal
 * @property string $AlamatDomisili
 * @property string $RT
 * @property string $RW
 * @property string $Kelurahan
 * @property string $Kecamatan
 * @property string $Kota
 * @property string $KodePos
 * @property string $NoHp
 * @property string $NamaDarurat
 * @property string $TelpDarurat
 * @property string $AlamatDarurat
 * @property string $StatusHubunganDarurat
 * @property string $City
 * @property string $Province
 * @property string $CityNow
 * @property string $ProvinceNow
 * @property string $JobNameDetail
 *
 * The followings are the available model relations:
 * @property Collectionloanitems[] $collectionloanitems
 * @property Collectionloans[] $collectionloans
 * @property Memberloanauthorizecategory[] $memberloanauthorizecategories
 * @property Memberloanauthorizelocation[] $memberloanauthorizelocations
 * @property Branchs $branch
 */
class Members extends CActiveRecord
{
	public $defaultColumns = array();

	/**
	 * Returns the static model of the specified AR class.
	 * Please note that you should have this exact method in all your CActiveRecord descendants!
	 * @param string $className active record class name.
	 * @return Members the static model class
	 */
	public static function model($className=__CLASS__)
	{
		return parent::model($className);
	}

	/**
	 * @return CDbConnection the database connection used for this class
	 */
	public function getDbConnection()
	{
		return Yii::app()->inlis;
	}

	/**
	 * @return string the associated database table name
	 */
	public function tableName()
	{
		return 'members';
	}

	/**
	 * @return array validation rules for model attributes.
	 */
	public function rules()
	{
		// NOTE: you should only define rules for those attributes that
		// will receive user inputs.
		return array(
			array('LoanReturnLateCount, Branch_id, User_id', 'numerical', 'integerOnly'=>true),
			array('MemberNo, Fullname, PlaceOfBirth, Address, AddressNow, Phone, InstitutionName, InstitutionAddress, InstitutionPhone, IdentityType, IdentityNo, EducationLevel, Religion, Sex, MaritalStatus, JobName, BarCode, PicPath, MotherMaidenName, Email, JenisPermohonan, JenisPermohonanName, JenisAnggota, JenisAnggotaName, StatusAnggota, StatusAnggotaName, Handphone, ParentName, ParentAddress, ParentPhone, ParentHandphone, Nationality, AlamatDomisili, RT, RW, Kelurahan, Kecamatan, Kota, KodePos, NoHp, NamaDarurat, TelpDarurat, AlamatDarurat, StatusHubunganDarurat', 'length', 'max'=>255),
			array('CreateBy, CreateTerminal, UpdateBy, UpdateTerminal', 'length', 'max'=>100),
			array('City, Province, CityNow, ProvinceNow', 'length', 'max'=>45),
			array('JobNameDetail', 'length', 'max'=>50),
			array('DateOfBirth, RegisterDate, EndDate, CreateDate, UpdateDate', 'safe'),
			// The following rule is used by search().
			// @todo Please remove those attributes that should not be searched.
			array('ID, MemberNo, Fullname, PlaceOfBirth, DateOfBirth, Address, AddressNow, Phone, InstitutionName, InstitutionAddress, InstitutionPhone, IdentityType, IdentityNo, EducationLevel, Religion, Sex, MaritalStatus, JobName, RegisterDate, EndDate, BarCode, PicPath, MotherMaidenName, Email, JenisPermohonan, JenisPermohonanName, JenisAnggota, JenisAnggotaName, StatusAnggota, StatusAnggotaName, Handphone, ParentName, ParentAddress, ParentPhone, ParentHandphone, Nationality, LoanReturnLateCount, Branch_id, User_id, CreateBy, CreateDate, CreateTerminal, UpdateBy, UpdateDate, UpdateTerminal, AlamatDomisili, RT, RW, Kelurahan, Kecamatan, Kota, KodePos, NoHp, NamaDarurat, TelpDarurat, AlamatDarurat, StatusHubunganDarurat, City, Province, CityNow, ProvinceNow, JobNameDetail', 'safe', 'on'=>'search'),
		);
	}

	/**
	 * @return array relational rules.
	 */
	public function relations()
	{
		// NOTE: you may need to adjust the relation name and the related
		// class name for the relations automatically generated below.
		return array(
			'collectionloanitems_relation' => array(self::HAS_MANY, 'Collectionloanitems', 'member_id'),
			'collectionloans_relation' => array(self::HAS_MANY, 'Collectionloans', 'Member_id'),
			'memberloanauthorizecategories_relation' => array(self::HAS_MANY, 'Memberloanauthorizecategory', 'Member_id'),
			'memberloanauthorizelocations_relation' => array(self::HAS_MANY, 'Memberloanauthorizelocation', 'Member_id'),
			'branch_relation' => array(self::BELONGS_TO, 'Branchs', 'Branch_id'),
		);
	}

	/**
	 * @return array customized attribute labels (name=>label)
	 */
	public function attributeLabels()
	{
		return array(
			'ID' => Yii::t('attribute', 'ID'),
			'MemberNo' => Yii::t('attribute', 'Member No'),
			'Fullname' => Yii::t('attribute', 'Fullname'),
			'PlaceOfBirth' => Yii::t('attribute', 'Place Of Birth'),
			'DateOfBirth' => Yii::t('attribute', 'Date Of Birth'),
			'Address' => Yii::t('attribute', 'Address'),
			'AddressNow' => Yii::t('attribute', 'Address Now'),
			'Phone' => Yii::t('attribute', 'Phone'),
			'InstitutionName' => Yii::t('attribute', 'Institution Name'),
			'InstitutionAddress' => Yii::t('attribute', 'Institution Address'),
			'InstitutionPhone' => Yii::t('attribute', 'Institution Phone'),
			'IdentityType' => Yii::t('attribute', 'Identity Type'),
			'IdentityNo' => Yii::t('attribute', 'Identity No'),
			'EducationLevel' => Yii::t('attribute', 'Education Level'),
			'Religion' => Yii::t('attribute', 'Religion'),
			'Sex' => Yii::t('attribute', 'Sex'),
			'MaritalStatus' => Yii::t('attribute', 'Marital Status'),
			'JobName' => Yii::t('attribute', 'Job Name'),
			'RegisterDate' => Yii::t('attribute', 'Register Date'),
			'EndDate' => Yii::t('attribute', 'End Date'),
			'BarCode' => Yii::t('attribute', 'Bar Code'),
			'PicPath' => Yii::t('attribute', 'Pic Path'),
			'MotherMaidenName' => Yii::t('attribute', 'Mother Maiden Name'),
			'Email' => Yii::t('attribute', 'Email'),
			'JenisPermohonan' => Yii::t('attribute', 'Jenis Permohonan'),
			'JenisPermohonanName' => Yii::t('attribute', 'Jenis Permohonan Name'),
			'JenisAnggota' => Yii::t('attribute', 'Jenis Anggota'),
			'JenisAnggotaName' => Yii::t('attribute', 'Jenis Anggota Name'),
			'StatusAnggota' => Yii::t('attribute', 'Status Anggota'),
			'StatusAnggotaName' => Yii::t('attribute', 'Status Anggota Name'),
			'Handphone' => Yii::t('attribute', 'Handphone'),
			'ParentName' => Yii::t('attribute', 'Parent Name'),
			'ParentAddress' => Yii::t('attribute', 'Parent Address'),
			'ParentPhone' => Yii::t('attribute', 'Parent Phone'),
			'ParentHandphone' => Yii::t('attribute', 'Parent Handphone'),
			'Nationality' => Yii::t('attribute', 'Nationality'),
			'LoanReturnLateCount' => Yii::t('attribute', 'Loan Return Late Count'),
			'Branch_id' => Yii::t('attribute', 'Branch'),
			'User_id' => Yii::t('attribute', 'User'),
			'CreateBy' => Yii::t('attribute', 'Create By'),
			'CreateDate' => Yii::t('attribute', 'Create Date'),
			'CreateTerminal' => Yii::t('attribute', 'Create Terminal'),
			'UpdateBy' => Yii::t('attribute', 'Update By'),
			'UpdateDate' => Yii::t('attribute', 'Update Date'),
			'UpdateTerminal' => Yii::t('attribute', 'Update Terminal'),
			'AlamatDomisili' => Yii::t('attribute', 'Alamat Domisili'),
			'RT' => Yii::t('attribute', 'Rt'),
			'RW' => Yii::t('attribute', 'Rw'),
			'Kelurahan' => Yii::t('attribute', 'Kelurahan'),
			'Kecamatan' => Yii::t('attribute', 'Kecamatan'),
			'Kota' => Yii::t('attribute', 'Kota'),
			'KodePos' => Yii::t('attribute', 'Kode Pos'),
			'NoHp' => Yii::t('attribute', 'No Hp'),
			'NamaDarurat' => Yii::t('attribute', 'Nama Darurat'),
			'TelpDarurat' => Yii::t('attribute', 'Telp Darurat'),
			'AlamatDarurat' => Yii::t('attribute', 'Alamat Darurat'),
			'StatusHubunganDarurat' => Yii::t('attribute', 'Status Hubungan Darurat'),
			'City' => Yii::t('attribute', 'City'),
			'Province' => Yii::t('attribute', 'Province'),
			'CityNow' => Yii::t('attribute', 'City Now'),
			'ProvinceNow' => Yii::t('attribute', 'Province Now'),
			'JobNameDetail' => Yii::t('attribute', 'Job Name Detail'),
		);
		/*
			'ID' => 'ID',
			'Member No' => 'Member No',
			'Fullname' => 'Fullname',
			'Place Of Birth' => 'Place Of Birth',
			'Date Of Birth' => 'Date Of Birth',
			'Address' => 'Address',
			'Address Now' => 'Address Now',
			'Phone' => 'Phone',
			'Institution Name' => 'Institution Name',
			'Institution Address' => 'Institution Address',
			'Institution Phone' => 'Institution Phone',
			'Identity Type' => 'Identity Type',
			'Identity No' => 'Identity No',
			'Education Level' => 'Education Level',
			'Religion' => 'Religion',
			'Sex' => 'Sex',
			'Marital Status' => 'Marital Status',
			'Job Name' => 'Job Name',
			'Register Date' => 'Register Date',
			'End Date' => 'End Date',
			'Bar Code' => 'Bar Code',
			'Pic Path' => 'Pic Path',
			'Mother Maiden Name' => 'Mother Maiden Name',
			'Email' => 'Email',
			'Jenis Permohonan' => 'Jenis Permohonan',
			'Jenis Permohonan Name' => 'Jenis Permohonan Name',
			'Jenis Anggota' => 'Jenis Anggota',
			'Jenis Anggota Name' => 'Jenis Anggota Name',
			'Status Anggota' => 'Status Anggota',
			'Status Anggota Name' => 'Status Anggota Name',
			'Handphone' => 'Handphone',
			'Parent Name' => 'Parent Name',
			'Parent Address' => 'Parent Address',
			'Parent Phone' => 'Parent Phone',
			'Parent Handphone' => 'Parent Handphone',
			'Nationality' => 'Nationality',
			'Loan Return Late Count' => 'Loan Return Late Count',
			'Branch' => 'Branch',
			'User' => 'User',
			'Create By' => 'Create By',
			'Create Date' => 'Create Date',
			'Create Terminal' => 'Create Terminal',
			'Update By' => 'Update By',
			'Update Date' => 'Update Date',
			'Update Terminal' => 'Update Terminal',
			'Alamat Domisili' => 'Alamat Domisili',
			'Rt' => 'Rt',
			'Rw' => 'Rw',
			'Kelurahan' => 'Kelurahan',
			'Kecamatan' => 'Kecamatan',
			'Kota' => 'Kota',
			'Kode Pos' => 'Kode Pos',
			'No Hp' => 'No Hp',
			'Nama Darurat' => 'Nama Darurat',
			'Telp Darurat' => 'Telp Darurat',
			'Alamat Darurat' => 'Alamat Darurat',
			'Status Hubungan Darurat' => 'Status Hubungan Darurat',
			'City' => 'City',
			'Province' => 'Province',
			'City Now' => 'City Now',
			'Province Now' => 'Province Now',
			'Job Name Detail' => 'Job Name Detail',
		
		*/
	}

	/**
	 * Retrieves a list of models based on the current search/filter conditions.
	 *
	 * Typical usecase:
	 * - Initialize the model fields with values from filter form.
	 * - Execute this method to get CActiveDataProvider instance which will filter
	 * models according to data in model fields.
	 * - Pass data provider to CGridView, CListView or any similar widget.
	 *
	 * @return CActiveDataProvider the data provider that can return the models
	 * based on the search/filter conditions.
	 */
	public function search()
	{
		// @todo Please modify the following code to remove attributes that should not be searched.

		$criteria=new CDbCriteria;

		$criteria->compare('t.ID',$this->ID);
		$criteria->compare('t.MemberNo',strtolower($this->MemberNo),true);
		$criteria->compare('t.Fullname',strtolower($this->Fullname),true);
		$criteria->compare('t.PlaceOfBirth',strtolower($this->PlaceOfBirth),true);
		if($this->DateOfBirth != null && !in_array($this->DateOfBirth, array('0000-00-00 00:00:00', '0000-00-00')))
			$criteria->compare('date(t.DateOfBirth)',date('Y-m-d', strtotime($this->DateOfBirth)));
		$criteria->compare('t.Address',strtolower($this->Address),true);
		$criteria->compare('t.AddressNow',strtolower($this->AddressNow),true);
		$criteria->compare('t.Phone',strtolower($this->Phone),true);
		$criteria->compare('t.InstitutionName',strtolower($this->InstitutionName),true);
		$criteria->compare('t.InstitutionAddress',strtolower($this->InstitutionAddress),true);
		$criteria->compare('t.InstitutionPhone',strtolower($this->InstitutionPhone),true);
		$criteria->compare('t.IdentityType',strtolower($this->IdentityType),true);
		$criteria->compare('t.IdentityNo',strtolower($this->IdentityNo),true);
		$criteria->compare('t.EducationLevel',strtolower($this->EducationLevel),true);
		$criteria->compare('t.Religion',strtolower($this->Religion),true);
		$criteria->compare('t.Sex',strtolower($this->Sex),true);
		$criteria->compare('t.MaritalStatus',strtolower($this->MaritalStatus),true);
		$criteria->compare('t.JobName',strtolower($this->JobName),true);
		if($this->RegisterDate != null && !in_array($this->RegisterDate, array('0000-00-00 00:00:00', '0000-00-00')))
			$criteria->compare('date(t.RegisterDate)',date('Y-m-d', strtotime($this->RegisterDate)));
		if($this->EndDate != null && !in_array($this->EndDate, array('0000-00-00 00:00:00', '0000-00-00')))
			$criteria->compare('date(t.EndDate)',date('Y-m-d', strtotime($this->EndDate)));
		$criteria->compare('t.BarCode',strtolower($this->BarCode),true);
		$criteria->compare('t.PicPath',strtolower($this->PicPath),true);
		$criteria->compare('t.MotherMaidenName',strtolower($this->MotherMaidenName),true);
		$criteria->compare('t.Email',strtolower($this->Email),true);
		$criteria->compare('t.JenisPermohonan',strtolower($this->JenisPermohonan),true);
		$criteria->compare('t.JenisPermohonanName',strtolower($this->JenisPermohonanName),true);
		$criteria->compare('t.JenisAnggota',strtolower($this->JenisAnggota),true);
		$criteria->compare('t.JenisAnggotaName',strtolower($this->JenisAnggotaName),true);
		$criteria->compare('t.StatusAnggota',strtolower($this->StatusAnggota),true);
		$criteria->compare('t.StatusAnggotaName',strtolower($this->StatusAnggotaName),true);
		$criteria->compare('t.Handphone',strtolower($this->Handphone),true);
		$criteria->compare('t.ParentName',strtolower($this->ParentName),true);
		$criteria->compare('t.ParentAddress',strtolower($this->ParentAddress),true);
		$criteria->compare('t.ParentPhone',strtolower($this->ParentPhone),true);
		$criteria->compare('t.ParentHandphone',strtolower($this->ParentHandphone),true);
		$criteria->compare('t.Nationality',strtolower($this->Nationality),true);
		$criteria->compare('t.LoanReturnLateCount',$this->LoanReturnLateCount);
		if(isset($_GET['Branch']))
			$criteria->compare('t.Branch_id',$_GET['Branch']);
		else
			$criteria->compare('t.Branch_id',$this->Branch_id);
		$criteria->compare('t.User_id',$this->User_id);
		$criteria->compare('t.CreateBy',strtolower($this->CreateBy),true);
		if($this->CreateDate != null && !in_array($this->CreateDate, array('0000-00-00 00:00:00', '0000-00-00')))
			$criteria->compare('date(t.CreateDate)',date('Y-m-d', strtotime($this->CreateDate)));
		$criteria->compare('t.CreateTerminal',strtolower($this->CreateTerminal),true);
		$criteria->compare('t.UpdateBy',strtolower($this->UpdateBy),true);
		if($this->UpdateDate != null && !in_array($this->UpdateDate, array('0000-00-00 00:00:00', '0000-00-00')))
			$criteria->compare('date(t.UpdateDate)',date('Y-m-d', strtotime($this->UpdateDate)));
		$criteria->compare('t.UpdateTerminal',strtolower($this->UpdateTerminal),true);
		$criteria->compare('t.AlamatDomisili',strtolower($this->AlamatDomisili),true);
		$criteria->compare('t.RT',strtolower($this->RT),true);
		$criteria->compare('t.RW',strtolower($this->RW),true);
		$criteria->compare('t.Kelurahan',strtolower($this->Kelurahan),true);
		$criteria->compare('t.Kecamatan',strtolower($this->Kecamatan),true);
		$criteria->compare('t.Kota',strtolower($this->Kota),true);
		$criteria->compare('t.KodePos',strtolower($this->KodePos),true);
		$criteria->compare('t.NoHp',strtolower($this->NoHp),true);
		$criteria->compare('t.NamaDarurat',strtolower($this->NamaDarurat),true);
		$criteria->compare('t.TelpDarurat',strtolower($this->TelpDarurat),true);
		$criteria->compare('t.AlamatDarurat',strtolower($this->AlamatDarurat),true);
		$criteria->compare('t.StatusHubunganDarurat',strtolower($this->StatusHubunganDarurat),true);
		$criteria->compare('t.City',strtolower($this->City),true);
		$criteria->compare('t.Province',strtolower($this->Province),true);
		$criteria->compare('t.CityNow',strtolower($this->CityNow),true);
		$criteria->compare('t.ProvinceNow',strtolower($this->ProvinceNow),true);
		$criteria->compare('t.JobNameDetail',strtolower($this->JobNameDetail),true);

		if(!isset($_GET['Members_sort']))
			$criteria->order = 't.ID DESC';

		return new CActiveDataProvider($this, array(
			'criteria'=>$criteria,
			'pagination'=>array(
				'pageSize'=>30,
			),
		));
	}


	/**
	 * Get column for CGrid View
	 */
	public function getGridColumn($columns=null) {
		if($columns !== null) {
			foreach($columns as $val) {
				/*
				if(trim($val) == 'enabled') {
					$this->defaultColumns[] = array(
						'name'  => 'enabled',
						'value' => '$data->enabled == 1? "Ya": "Tidak"',
					);
				}
				*/
				$this->defaultColumns[] = $val;
			}
		} else {
			//$this->defaultColumns[] = 'ID';
			$this->defaultColumns[] = 'MemberNo';
			$this->defaultColumns[] = 'Fullname';
			$this->defaultColumns[] = 'PlaceOfBirth';
			$this->defaultColumns[] = 'DateOfBirth';
			$this->defaultColumns[] = 'Address';
			$this->defaultColumns[] = 'AddressNow';
			$this->defaultColumns[] = 'Phone';
			$this->defaultColumns[] = 'InstitutionName';
			$this->defaultColumns[] = 'InstitutionAddress';
			$this->defaultColumns[] = 'InstitutionPhone';
			$this->defaultColumns[] = 'IdentityType';
			$this->defaultColumns[] = 'IdentityNo';
			$this->defaultColumns[] = 'EducationLevel';
			$this->defaultColumns[] = 'Religion';
			$this->defaultColumns[] = 'Sex';
			$this->defaultColumns[] = 'MaritalStatus';
			$this->defaultColumns[] = 'JobName';
			$this->defaultColumns[] = 'RegisterDate';
			$this->defaultColumns[] = 'EndDate';
			$this->defaultColumns[] = 'BarCode';
			$this->defaultColumns[] = 'PicPath';
			$this->defaultColumns[] = 'MotherMaidenName';
			$this->defaultColumns[] = 'Email';
			$this->defaultColumns[] = 'JenisPermohonan';
			$this->defaultColumns[] = 'JenisPermohonanName';
			$this->defaultColumns[] = 'JenisAnggota';
			$this->defaultColumns[] = 'JenisAnggotaName';
			$this->defaultColumns[] = 'StatusAnggota';
			$this->defaultColumns[] = 'StatusAnggotaName';
			$this->defaultColumns[] = 'Handphone';
			$this->defaultColumns[] = 'ParentName';
			$this->defaultColumns[] = 'ParentAddress';
			$this->defaultColumns[] = 'ParentPhone';
			$this->defaultColumns[] = 'ParentHandphone';
			$this->defaultColumns[] = 'Nationality';
			$this->defaultColumns[] = 'LoanReturnLateCount';
			$this->defaultColumns[] = 'Branch_id';
			$this->defaultColumns[] = 'User_id';
			$this->defaultColumns[] = 'CreateBy';
			$this->defaultColumns[] = 'CreateDate';
			$this->defaultColumns[] = 'CreateTerminal';
			$this->defaultColumns[] = 'UpdateBy';
			$this->defaultColumns[] = 'UpdateDate';
			$this->defaultColumns[] = 'UpdateTerminal';
			$this->defaultColumns[] = 'AlamatDomisili';
			$this->defaultColumns[] = 'RT';
			$this->defaultColumns[] = 'RW';
			$this->defaultColumns[] = 'Kelurahan';
			$this->defaultColumns[] = 'Kecamatan';
			$this->defaultColumns[] = 'Kota';
			$this->defaultColumns[] = 'KodePos';
			$this->defaultColumns[] = 'NoHp';
			$this->defaultColumns[] = 'NamaDarurat';
			$this->defaultColumns[] = 'TelpDarurat';
			$this->defaultColumns[] = 'AlamatDarurat';
			$this->defaultColumns[] = 'StatusHubunganDarurat';
			$this->defaultColumns[] = 'City';
			$this->defaultColumns[] = 'Province';
			$this->defaultColumns[] = 'CityNow';
			$this->defaultColumns[] = 'ProvinceNow';
			$this->defaultColumns[] = 'JobNameDetail';
		}

		return $this->defaultColumns;
	}

	/**
	 * Set default columns to display
	 */
	protected function afterConstruct() {
		if(count($this->defaultColumns) == 0) {
			/*
			$this->defaultColumns[] = array(
				'class' => 'CCheckBoxColumn',
				'name' => 'id',
				'selectableRows' => 2,
				'checkBoxHtmlOptions' => array('name' => 'trash_id[]')
			);
			*/
			$this->defaultColumns[] = array(
				'header' => 'No',
				'value' => '$this->grid->dataProvider->pagination->currentPage*$this->grid->dataProvider->pagination->pageSize + $row+1'
			);
			$this->defaultColumns[] = 'MemberNo';
			$this->defaultColumns[] = 'Fullname';
			$this->defaultColumns[] = 'PlaceOfBirth';
			$this->defaultColumns[] = array(
				'name' => 'DateOfBirth',
				'value' => 'Utility::dateFormat($data->DateOfBirth)',
				'htmlOptions' => array(
					'class' => 'center',
				),
				'filter' => Yii::app()->controller->widget('zii.widgets.jui.CJuiDatePicker', array(
					'model'=>$this,
					'attribute'=>'DateOfBirth',
					'language' => 'ja',
					'i18nScriptFile' => 'jquery.ui.datepicker-en.js',
					//'mode'=>'datetime',
					'htmlOptions' => array(
						'id' => 'DateOfBirth_filter',
					),
					'options'=>array(
						'showOn' => 'focus',
						'dateFormat' => 'dd-mm-yy',
						'showOtherMonths' => true,
						'selectOtherMonths' => true,
						'changeMonth' => true,
						'changeYear' => true,
						'showButtonPanel' => true,
					),
				), true),
			);
			$this->defaultColumns[] = 'Address';
			$this->defaultColumns[] = 'AddressNow';
			$this->defaultColumns[] = 'Phone';
			$this->defaultColumns[] = 'InstitutionName';
			$this->defaultColumns[] = 'InstitutionAddress';
			$this->defaultColumns[] = 'InstitutionPhone';
			$this->defaultColumns[] = 'IdentityType';
			$this->defaultColumns[] = 'IdentityNo';
			$this->defaultColumns[] = 'EducationLevel';
			$this->defaultColumns[] = 'Religion';
			$this->defaultColumns[] = 'Sex';
			$this->defaultColumns[] = 'MaritalStatus';
			$this->defaultColumns[] = 'JobName';
			$this->defaultColumns[] = array(
				'name' => 'RegisterDate',
				'value' => 'Utility::dateFormat($data->RegisterDate)',
				'htmlOptions' => array(
					'class' => 'center',
				),
				'filter' => Yii::app()->controller->widget('zii.widgets.jui.CJuiDatePicker', array(
					'model'=>$this,
					'attribute'=>'RegisterDate',
					'language' => 'ja',
					'i18nScriptFile' => 'jquery.ui.datepicker-en.js',
					//'mode'=>'datetime',
					'htmlOptions' => array(
						'id' => 'RegisterDate_filter',
					),
					'options'=>array(
						'showOn' => 'focus',
						'dateFormat' => 'dd-mm-yy',
						'showOtherMonths' => true,
						'selectOtherMonths' => true,
						'changeMonth' => true,
						'changeYear' => true,
						'showButtonPanel' => true,
					),
				), true),
			);
			$this->defaultColumns[] = array(
				'name' => 'EndDate',
				'value' => 'Utility::dateFormat($data->EndDate)',
				'htmlOptions' => array(
					'class' => 'center',
				),
				'filter' => Yii::app()->controller->widget('zii.widgets.jui.CJuiDatePicker', array(
					'model'=>$this,
					'attribute'=>'EndDate',
					'language' => 'ja',
					'i18nScriptFile' => 'jquery.ui.datepicker-en.js',
					//'mode'=>'datetime',
					'htmlOptions' => array(
						'id' => 'EndDate_filter',
					),
					'options'=>array(
						'showOn' => 'focus',
						'dateFormat' => 'dd-mm-yy',
						'showOtherMonths' => true,
						'selectOtherMonths' => true,
						'changeMonth' => true,
						'changeYear' => true,
						'showButtonPanel' => true,
					),
				), true),
			);
			$this->defaultColumns[] = 'BarCode';
			$this->defaultColumns[] = 'PicPath';
			$this->defaultColumns[] = 'MotherMaidenName';
			$this->defaultColumns[] = 'Email';
			$this->defaultColumns[] = 'JenisPermohonan';
			$this->defaultColumns[] = 'JenisPermohonanName';
			$this->defaultColumns[] = 'JenisAnggota';
			$this->defaultColumns[] = 'JenisAnggotaName';
			$this->defaultColumns[] = 'StatusAnggota';
			$this->defaultColumns[] = 'StatusAnggotaName';
			$this->defaultColumns[] = 'Handphone';
			$this->defaultColumns[] = 'ParentName';
			$this->defaultColumns[] = 'ParentAddress';
			$this->defaultColumns[] = 'ParentPhone';
			$this->defaultColumns[] = 'ParentHandphone';
			$this->defaultColumns[] = 'Nationality';
			$this->defaultColumns[] = 'LoanReturnLateCount';
			$this->defaultColumns[] = 'Branch_id';
			$this->defaultColumns[] = 'User_id';
			$this->defaultColumns[] = 'CreateBy';
			$this->defaultColumns[] = array(
				'name' => 'CreateDate',
				'value' => 'Utility::dateFormat($data->CreateDate)',
				'htmlOptions' => array(
					'class' => 'center',
				),
				'filter' => Yii::app()->controller->widget('zii.widgets.jui.CJuiDatePicker', array(
					'model'=>$this,
					'attribute'=>'CreateDate',
					'language' => 'ja',
					'i18nScriptFile' => 'jquery.ui.datepicker-en.js',
					//'mode'=>'datetime',
					'htmlOptions' => array(
						'id' => 'CreateDate_filter',
					),
					'options'=>array(
						'showOn' => 'focus',
						'dateFormat' => 'dd-mm-yy',
						'showOtherMonths' => true,
						'selectOtherMonths' => true,
						'changeMonth' => true,
						'changeYear' => true,
						'showButtonPanel' => true,
					),
				), true),
			);
			$this->defaultColumns[] = 'CreateTerminal';
			$this->defaultColumns[] = 'UpdateBy';
			$this->defaultColumns[] = array(
				'name' => 'UpdateDate',
				'value' => 'Utility::dateFormat($data->UpdateDate)',
				'htmlOptions' => array(
					'class' => 'center',
				),
				'filter' => Yii::app()->controller->widget('zii.widgets.jui.CJuiDatePicker', array(
					'model'=>$this,
					'attribute'=>'UpdateDate',
					'language' => 'ja',
					'i18nScriptFile' => 'jquery.ui.datepicker-en.js',
					//'mode'=>'datetime',
					'htmlOptions' => array(
						'id' => 'UpdateDate_filter',
					),
					'options'=>array(
						'showOn' => 'focus',
						'dateFormat' => 'dd-mm-yy',
						'showOtherMonths' => true,
						'selectOtherMonths' => true,
						'changeMonth' => true,
						'changeYear' => true,
						'showButtonPanel' => true,
					),
				), true),
			);
			$this->defaultColumns[] = 'UpdateTerminal';
			$this->defaultColumns[] = 'AlamatDomisili';
			$this->defaultColumns[] = 'RT';
			$this->defaultColumns[] = 'RW';
			$this->defaultColumns[] = 'Kelurahan';
			$this->defaultColumns[] = 'Kecamatan';
			$this->defaultColumns[] = 'Kota';
			$this->defaultColumns[] = 'KodePos';
			$this->defaultColumns[] = 'NoHp';
			$this->defaultColumns[] = 'NamaDarurat';
			$this->defaultColumns[] = 'TelpDarurat';
			$this->defaultColumns[] = 'AlamatDarurat';
			$this->defaultColumns[] = 'StatusHubunganDarurat';
			$this->defaultColumns[] = 'City';
			$this->defaultColumns[] = 'Province';
			$this->defaultColumns[] = 'CityNow';
			$this->defaultColumns[] = 'ProvinceNow';
			$this->defaultColumns[] = 'JobNameDetail';
		}
		parent::afterConstruct();
	}

	/**
	 * User get information
	 */
	public static function getInfo($id, $column=null)
	{
		if($column != null) {
			$model = self::model()->findByPk($id,array(
				'select' => $column
			));
			return $model->$column;
			
		} else {
			$model = self::model()->findByPk($id);
			return $model;			
		}
	}

	/**
	 * before validate attributes
	 */
	/*
	protected function beforeValidate() {
		if(parent::beforeValidate()) {
			// Create action
		}
		return true;
	}
	*/

	/**
	 * after validate attributes
	 */
	/*
	protected function afterValidate()
	{
		parent::afterValidate();
			// Create action
		return true;
	}
	*/
	
	/**
	 * before save attributes
	 */
	/*
	protected function beforeSave() {
		if(parent::beforeSave()) {
			//$this->DateOfBirth = date('Y-m-d', strtotime($this->DateOfBirth));
			//$this->RegisterDate = date('Y-m-d', strtotime($this->RegisterDate));
			//$this->EndDate = date('Y-m-d', strtotime($this->EndDate));
			//$this->CreateDate = date('Y-m-d', strtotime($this->CreateDate));
			//$this->UpdateDate = date('Y-m-d', strtotime($this->UpdateDate));
		}
		return true;	
	}
	*/
	
	/**
	 * After save attributes
	 */
	/*
	protected function afterSave() {
		parent::afterSave();
		// Create action
	}
	*/

	/**
	 * Before delete attributes
	 */
	/*
	protected function beforeDelete() {
		if(parent::beforeDelete()) {
			// Create action
		}
		return true;
	}
	*/

	/**
	 * After delete attributes
	 */
	/*
	protected function afterDelete() {
		parent::afterDelete();
		// Create action
	}
	*/

}