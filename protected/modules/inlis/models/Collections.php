<?php
/**
 * Collections
 * @author Putra Sudaryanto <putra.sudaryanto@gmail.com>
 * @copyright Copyright (c) 2016 Ommu Platform (ommu.co)
 * @created date 28 March 2016, 13:45 WIB
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
 * This is the model class for table "collections".
 *
 * The followings are the available columns in table 'collections':
 * @property double $ID
 * @property string $NoInduk
 * @property string $Title
 * @property string $TitleAdded
 * @property string $Author
 * @property string $AuthorAdded
 * @property string $Cooperation
 * @property string $PublishLocation
 * @property string $Publisher
 * @property string $PublishYear
 * @property string $KalaTerbit
 * @property string $Edition
 * @property string $Class
 * @property string $PhysicalDescription
 * @property string $Note
 * @property string $Currency
 * @property string $ISBN
 * @property string $MapScale
 * @property string $MapNumber
 * @property string $MapSubject
 * @property string $RFID
 * @property double $Price
 * @property string $TanggalKirim
 * @property integer $IsDelete
 * @property integer $Branch_id
 * @property double $Catalog_id
 * @property integer $Partner_id
 * @property integer $Location_id
 * @property integer $Rule_id
 * @property integer $Category_id
 * @property integer $Media_id
 * @property integer $Source_id
 * @property integer $Worksheet_id
 * @property double $GroupingNumber
 * @property string $NomorBarcode
 * @property string $Status
 * @property string $Keterangan_Sumber
 * @property string $Penempatan
 * @property string $CreateBy
 * @property string $CreateDate
 * @property string $CreateTerminal
 * @property string $UpdateBy
 * @property string $UpdateDate
 * @property string $UpdateTerminal
 * @property integer $IsVerified
 * @property string $SLA_REGISTER
 * @property string $SENAYAN_ID
 * @property string $NCIBookMan_ID
 *
 * The followings are the available model relations:
 * @property Catalogs $catalog
 * @property Branchs $branch
 * @property Partners $partner
 */
class Collections extends CActiveRecord
{
	public $defaultColumns = array();

	/**
	 * Returns the static model of the specified AR class.
	 * Please note that you should have this exact method in all your CActiveRecord descendants!
	 * @param string $className active record class name.
	 * @return Collections the static model class
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
		return 'collections';
	}

	/**
	 * @return array validation rules for model attributes.
	 */
	public function rules()
	{
		// NOTE: you should only define rules for those attributes that
		// will receive user inputs.
		return array(
			array('IsDelete, Branch_id, Partner_id, Location_id, Rule_id, Category_id, Media_id, Source_id, Worksheet_id, IsVerified', 'numerical', 'integerOnly'=>true),
			array('Price, Catalog_id, GroupingNumber', 'numerical'),
			array('NoInduk, RFID', 'length', 'max'=>255),
			array('Cooperation, PublishLocation, PublishYear, KalaTerbit, Edition, Class, MapScale, MapNumber, MapSubject, Keterangan_Sumber, Penempatan', 'length', 'max'=>200),
			array('Currency', 'length', 'max'=>30),
			array('ISBN', 'length', 'max'=>2000),
			array('NomorBarcode, Status', 'length', 'max'=>50),
			array('CreateBy, CreateTerminal, UpdateBy, UpdateTerminal', 'length', 'max'=>100),
			array('SLA_REGISTER, SENAYAN_ID, NCIBookMan_ID', 'length', 'max'=>45),
			array('Title, TitleAdded, Author, AuthorAdded, Publisher, PhysicalDescription, Note, TanggalKirim, CreateDate, UpdateDate', 'safe'),
			// The following rule is used by search().
			// @todo Please remove those attributes that should not be searched.
			array('ID, NoInduk, Title, TitleAdded, Author, AuthorAdded, Cooperation, PublishLocation, Publisher, PublishYear, KalaTerbit, Edition, Class, PhysicalDescription, Note, Currency, ISBN, MapScale, MapNumber, MapSubject, RFID, Price, TanggalKirim, IsDelete, Branch_id, Catalog_id, Partner_id, Location_id, Rule_id, Category_id, Media_id, Source_id, Worksheet_id, GroupingNumber, NomorBarcode, Status, Keterangan_Sumber, Penempatan, CreateBy, CreateDate, CreateTerminal, UpdateBy, UpdateDate, UpdateTerminal, IsVerified, SLA_REGISTER, SENAYAN_ID, NCIBookMan_ID', 'safe', 'on'=>'search'),
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
			'catalog_relation' => array(self::BELONGS_TO, 'Catalogs', 'Catalog_id'),
			'branch_relation' => array(self::BELONGS_TO, 'Branchs', 'Branch_id'),
			'partner_relation' => array(self::BELONGS_TO, 'Partners', 'Partner_id'),
		);
	}

	/**
	 * @return array customized attribute labels (name=>label)
	 */
	public function attributeLabels()
	{
		return array(
			'ID' => Yii::t('attribute', 'ID'),
			'NoInduk' => Yii::t('attribute', 'No Induk'),
			'Title' => Yii::t('attribute', 'Title'),
			'TitleAdded' => Yii::t('attribute', 'Title Added'),
			'Author' => Yii::t('attribute', 'Author'),
			'AuthorAdded' => Yii::t('attribute', 'Author Added'),
			'Cooperation' => Yii::t('attribute', 'Cooperation'),
			'PublishLocation' => Yii::t('attribute', 'Publish Location'),
			'Publisher' => Yii::t('attribute', 'Publisher'),
			'PublishYear' => Yii::t('attribute', 'Publish Year'),
			'KalaTerbit' => Yii::t('attribute', 'Kala Terbit'),
			'Edition' => Yii::t('attribute', 'Edition'),
			'Class' => Yii::t('attribute', 'Class'),
			'PhysicalDescription' => Yii::t('attribute', 'Physical Description'),
			'Note' => Yii::t('attribute', 'Note'),
			'Currency' => Yii::t('attribute', 'Currency'),
			'ISBN' => Yii::t('attribute', 'Isbn'),
			'MapScale' => Yii::t('attribute', 'Map Scale'),
			'MapNumber' => Yii::t('attribute', 'Map Number'),
			'MapSubject' => Yii::t('attribute', 'Map Subject'),
			'RFID' => Yii::t('attribute', 'Rfid'),
			'Price' => Yii::t('attribute', 'Price'),
			'TanggalKirim' => Yii::t('attribute', 'Tanggal Kirim'),
			'IsDelete' => Yii::t('attribute', 'Is Delete'),
			'Branch_id' => Yii::t('attribute', 'Branch'),
			'Catalog_id' => Yii::t('attribute', 'Catalog'),
			'Partner_id' => Yii::t('attribute', 'Partner'),
			'Location_id' => Yii::t('attribute', 'Location'),
			'Rule_id' => Yii::t('attribute', 'Rule'),
			'Category_id' => Yii::t('attribute', 'Category'),
			'Media_id' => Yii::t('attribute', 'Media'),
			'Source_id' => Yii::t('attribute', 'Source'),
			'Worksheet_id' => Yii::t('attribute', 'Worksheet'),
			'GroupingNumber' => Yii::t('attribute', 'Grouping Number'),
			'NomorBarcode' => Yii::t('attribute', 'Nomor Barcode'),
			'Status' => Yii::t('attribute', 'Status'),
			'Keterangan_Sumber' => Yii::t('attribute', 'Keterangan Sumber'),
			'Penempatan' => Yii::t('attribute', 'Penempatan'),
			'CreateBy' => Yii::t('attribute', 'Create By'),
			'CreateDate' => Yii::t('attribute', 'Create Date'),
			'CreateTerminal' => Yii::t('attribute', 'Create Terminal'),
			'UpdateBy' => Yii::t('attribute', 'Update By'),
			'UpdateDate' => Yii::t('attribute', 'Update Date'),
			'UpdateTerminal' => Yii::t('attribute', 'Update Terminal'),
			'IsVerified' => Yii::t('attribute', 'Is Verified'),
			'SLA_REGISTER' => Yii::t('attribute', 'Sla Register'),
			'SENAYAN_ID' => Yii::t('attribute', 'Senayan'),
			'NCIBookMan_ID' => Yii::t('attribute', 'Ncibook Man'),
		);
		/*
			'ID' => 'ID',
			'No Induk' => 'No Induk',
			'Title' => 'Title',
			'Title Added' => 'Title Added',
			'Author' => 'Author',
			'Author Added' => 'Author Added',
			'Cooperation' => 'Cooperation',
			'Publish Location' => 'Publish Location',
			'Publisher' => 'Publisher',
			'Publish Year' => 'Publish Year',
			'Kala Terbit' => 'Kala Terbit',
			'Edition' => 'Edition',
			'Class' => 'Class',
			'Physical Description' => 'Physical Description',
			'Note' => 'Note',
			'Currency' => 'Currency',
			'Isbn' => 'Isbn',
			'Map Scale' => 'Map Scale',
			'Map Number' => 'Map Number',
			'Map Subject' => 'Map Subject',
			'Rfid' => 'Rfid',
			'Price' => 'Price',
			'Tanggal Kirim' => 'Tanggal Kirim',
			'Is Delete' => 'Is Delete',
			'Branch' => 'Branch',
			'Catalog' => 'Catalog',
			'Partner' => 'Partner',
			'Location' => 'Location',
			'Rule' => 'Rule',
			'Category' => 'Category',
			'Media' => 'Media',
			'Source' => 'Source',
			'Worksheet' => 'Worksheet',
			'Grouping Number' => 'Grouping Number',
			'Nomor Barcode' => 'Nomor Barcode',
			'Status' => 'Status',
			'Keterangan Sumber' => 'Keterangan Sumber',
			'Penempatan' => 'Penempatan',
			'Create By' => 'Create By',
			'Create Date' => 'Create Date',
			'Create Terminal' => 'Create Terminal',
			'Update By' => 'Update By',
			'Update Date' => 'Update Date',
			'Update Terminal' => 'Update Terminal',
			'Is Verified' => 'Is Verified',
			'Sla Register' => 'Sla Register',
			'Senayan' => 'Senayan',
			'Ncibook Man' => 'Ncibook Man',
		
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
		$criteria->compare('t.NoInduk',strtolower($this->NoInduk),true);
		$criteria->compare('t.Title',strtolower($this->Title),true);
		$criteria->compare('t.TitleAdded',strtolower($this->TitleAdded),true);
		$criteria->compare('t.Author',strtolower($this->Author),true);
		$criteria->compare('t.AuthorAdded',strtolower($this->AuthorAdded),true);
		$criteria->compare('t.Cooperation',strtolower($this->Cooperation),true);
		$criteria->compare('t.PublishLocation',strtolower($this->PublishLocation),true);
		$criteria->compare('t.Publisher',strtolower($this->Publisher),true);
		$criteria->compare('t.PublishYear',strtolower($this->PublishYear),true);
		$criteria->compare('t.KalaTerbit',strtolower($this->KalaTerbit),true);
		$criteria->compare('t.Edition',strtolower($this->Edition),true);
		$criteria->compare('t.Class',strtolower($this->Class),true);
		$criteria->compare('t.PhysicalDescription',strtolower($this->PhysicalDescription),true);
		$criteria->compare('t.Note',strtolower($this->Note),true);
		$criteria->compare('t.Currency',strtolower($this->Currency),true);
		$criteria->compare('t.ISBN',strtolower($this->ISBN),true);
		$criteria->compare('t.MapScale',strtolower($this->MapScale),true);
		$criteria->compare('t.MapNumber',strtolower($this->MapNumber),true);
		$criteria->compare('t.MapSubject',strtolower($this->MapSubject),true);
		$criteria->compare('t.RFID',strtolower($this->RFID),true);
		$criteria->compare('t.Price',$this->Price);
		if($this->TanggalKirim != null && !in_array($this->TanggalKirim, array('0000-00-00 00:00:00', '0000-00-00')))
			$criteria->compare('date(t.TanggalKirim)',date('Y-m-d', strtotime($this->TanggalKirim)));
		$criteria->compare('t.IsDelete',$this->IsDelete);
		if(isset($_GET['Branch']))
			$criteria->compare('t.Branch_id',$_GET['Branch']);
		else
			$criteria->compare('t.Branch_id',$this->Branch_id);
		if(isset($_GET['Catalog']))
			$criteria->compare('t.Catalog_id',$_GET['Catalog']);
		else
			$criteria->compare('t.Catalog_id',$this->Catalog_id);
		if(isset($_GET['Partner']))
			$criteria->compare('t.Partner_id',$_GET['Partner']);
		else
			$criteria->compare('t.Partner_id',$this->Partner_id);
		$criteria->compare('t.Location_id',$this->Location_id);
		$criteria->compare('t.Rule_id',$this->Rule_id);
		$criteria->compare('t.Category_id',$this->Category_id);
		$criteria->compare('t.Media_id',$this->Media_id);
		$criteria->compare('t.Source_id',$this->Source_id);
		$criteria->compare('t.Worksheet_id',$this->Worksheet_id);
		$criteria->compare('t.GroupingNumber',$this->GroupingNumber);
		$criteria->compare('t.NomorBarcode',strtolower($this->NomorBarcode),true);
		$criteria->compare('t.Status',strtolower($this->Status),true);
		$criteria->compare('t.Keterangan_Sumber',strtolower($this->Keterangan_Sumber),true);
		$criteria->compare('t.Penempatan',strtolower($this->Penempatan),true);
		$criteria->compare('t.CreateBy',strtolower($this->CreateBy),true);
		if($this->CreateDate != null && !in_array($this->CreateDate, array('0000-00-00 00:00:00', '0000-00-00')))
			$criteria->compare('date(t.CreateDate)',date('Y-m-d', strtotime($this->CreateDate)));
		$criteria->compare('t.CreateTerminal',strtolower($this->CreateTerminal),true);
		$criteria->compare('t.UpdateBy',strtolower($this->UpdateBy),true);
		if($this->UpdateDate != null && !in_array($this->UpdateDate, array('0000-00-00 00:00:00', '0000-00-00')))
			$criteria->compare('date(t.UpdateDate)',date('Y-m-d', strtotime($this->UpdateDate)));
		$criteria->compare('t.UpdateTerminal',strtolower($this->UpdateTerminal),true);
		$criteria->compare('t.IsVerified',$this->IsVerified);
		$criteria->compare('t.SLA_REGISTER',strtolower($this->SLA_REGISTER),true);
		$criteria->compare('t.SENAYAN_ID',strtolower($this->SENAYAN_ID),true);
		$criteria->compare('t.NCIBookMan_ID',strtolower($this->NCIBookMan_ID),true);

		if(!isset($_GET['Collections_sort']))
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
			$this->defaultColumns[] = 'NoInduk';
			$this->defaultColumns[] = 'Title';
			$this->defaultColumns[] = 'TitleAdded';
			$this->defaultColumns[] = 'Author';
			$this->defaultColumns[] = 'AuthorAdded';
			$this->defaultColumns[] = 'Cooperation';
			$this->defaultColumns[] = 'PublishLocation';
			$this->defaultColumns[] = 'Publisher';
			$this->defaultColumns[] = 'PublishYear';
			$this->defaultColumns[] = 'KalaTerbit';
			$this->defaultColumns[] = 'Edition';
			$this->defaultColumns[] = 'Class';
			$this->defaultColumns[] = 'PhysicalDescription';
			$this->defaultColumns[] = 'Note';
			$this->defaultColumns[] = 'Currency';
			$this->defaultColumns[] = 'ISBN';
			$this->defaultColumns[] = 'MapScale';
			$this->defaultColumns[] = 'MapNumber';
			$this->defaultColumns[] = 'MapSubject';
			$this->defaultColumns[] = 'RFID';
			$this->defaultColumns[] = 'Price';
			$this->defaultColumns[] = 'TanggalKirim';
			$this->defaultColumns[] = 'IsDelete';
			$this->defaultColumns[] = 'Branch_id';
			$this->defaultColumns[] = 'Catalog_id';
			$this->defaultColumns[] = 'Partner_id';
			$this->defaultColumns[] = 'Location_id';
			$this->defaultColumns[] = 'Rule_id';
			$this->defaultColumns[] = 'Category_id';
			$this->defaultColumns[] = 'Media_id';
			$this->defaultColumns[] = 'Source_id';
			$this->defaultColumns[] = 'Worksheet_id';
			$this->defaultColumns[] = 'GroupingNumber';
			$this->defaultColumns[] = 'NomorBarcode';
			$this->defaultColumns[] = 'Status';
			$this->defaultColumns[] = 'Keterangan_Sumber';
			$this->defaultColumns[] = 'Penempatan';
			$this->defaultColumns[] = 'CreateBy';
			$this->defaultColumns[] = 'CreateDate';
			$this->defaultColumns[] = 'CreateTerminal';
			$this->defaultColumns[] = 'UpdateBy';
			$this->defaultColumns[] = 'UpdateDate';
			$this->defaultColumns[] = 'UpdateTerminal';
			$this->defaultColumns[] = 'IsVerified';
			$this->defaultColumns[] = 'SLA_REGISTER';
			$this->defaultColumns[] = 'SENAYAN_ID';
			$this->defaultColumns[] = 'NCIBookMan_ID';
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
			$this->defaultColumns[] = 'NoInduk';
			$this->defaultColumns[] = 'Title';
			$this->defaultColumns[] = 'TitleAdded';
			$this->defaultColumns[] = 'Author';
			$this->defaultColumns[] = 'AuthorAdded';
			$this->defaultColumns[] = 'Cooperation';
			$this->defaultColumns[] = 'PublishLocation';
			$this->defaultColumns[] = 'Publisher';
			$this->defaultColumns[] = 'PublishYear';
			$this->defaultColumns[] = 'KalaTerbit';
			$this->defaultColumns[] = 'Edition';
			$this->defaultColumns[] = 'Class';
			$this->defaultColumns[] = 'PhysicalDescription';
			$this->defaultColumns[] = 'Note';
			$this->defaultColumns[] = 'Currency';
			$this->defaultColumns[] = 'ISBN';
			$this->defaultColumns[] = 'MapScale';
			$this->defaultColumns[] = 'MapNumber';
			$this->defaultColumns[] = 'MapSubject';
			$this->defaultColumns[] = 'RFID';
			$this->defaultColumns[] = 'Price';
			$this->defaultColumns[] = array(
				'name' => 'TanggalKirim',
				'value' => 'Utility::dateFormat($data->TanggalKirim)',
				'htmlOptions' => array(
					'class' => 'center',
				),
				'filter' => Yii::app()->controller->widget('zii.widgets.jui.CJuiDatePicker', array(
					'model'=>$this,
					'attribute'=>'TanggalKirim',
					'language' => 'ja',
					'i18nScriptFile' => 'jquery.ui.datepicker-en.js',
					//'mode'=>'datetime',
					'htmlOptions' => array(
						'id' => 'TanggalKirim_filter',
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
			if(!isset($_GET['type'])) {
				$this->defaultColumns[] = array(
					'name' => 'IsDelete',
					'value' => 'Utility::getPublish(Yii::app()->controller->createUrl("IsDelete",array("id"=>$data->ID)), $data->IsDelete, 1)',
					'htmlOptions' => array(
						'class' => 'center',
					),
					'filter'=>array(
						1=>Phrase::trans(588,0),
						0=>Phrase::trans(589,0),
					),
					'type' => 'raw',
				);
			}
			$this->defaultColumns[] = 'Branch_id';
			$this->defaultColumns[] = 'Catalog_id';
			$this->defaultColumns[] = 'Partner_id';
			$this->defaultColumns[] = 'Location_id';
			$this->defaultColumns[] = 'Rule_id';
			$this->defaultColumns[] = 'Category_id';
			$this->defaultColumns[] = 'Media_id';
			$this->defaultColumns[] = 'Source_id';
			$this->defaultColumns[] = 'Worksheet_id';
			$this->defaultColumns[] = 'GroupingNumber';
			$this->defaultColumns[] = 'NomorBarcode';
			$this->defaultColumns[] = 'Status';
			$this->defaultColumns[] = 'Keterangan_Sumber';
			$this->defaultColumns[] = 'Penempatan';
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
			$this->defaultColumns[] = 'IsVerified';
			$this->defaultColumns[] = 'SLA_REGISTER';
			$this->defaultColumns[] = 'SENAYAN_ID';
			$this->defaultColumns[] = 'NCIBookMan_ID';
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
			//$this->TanggalKirim = date('Y-m-d', strtotime($this->TanggalKirim));
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