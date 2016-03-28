<?php
/**
 * Catalogs
 * @author Putra Sudaryanto <putra.sudaryanto@gmail.com>
 * @copyright Copyright (c) 2016 Ommu Platform (ommu.co)
 * @created date 28 March 2016, 13:46 WIB
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
 * This is the model class for table "catalogs".
 *
 * The followings are the available columns in table 'catalogs':
 * @property double $ID
 * @property string $ControlNumber
 * @property string $BIBID
 * @property string $Title
 * @property string $Author
 * @property string $Edition
 * @property string $Publisher
 * @property string $PublishLocation
 * @property string $PublishYear
 * @property string $Paging
 * @property string $Ill
 * @property string $Sizes
 * @property string $Item
 * @property string $Subject
 * @property string $Description
 * @property string $ISBN
 * @property string $CallNumber
 * @property string $Note
 * @property string $Languages
 * @property string $DeweyNo
 * @property string $ApproveDateOPAC
 * @property integer $IsOPAC
 * @property integer $IsBNI
 * @property integer $IsKIN
 * @property integer $IsDelete
 * @property string $CoverURL
 * @property string $FileURL
 * @property string $MARC
 * @property integer $Branch_id
 * @property integer $Worksheet_id
 * @property string $CreateBy
 * @property string $CreateDate
 * @property string $CreateTerminal
 * @property string $UpdateBy
 * @property string $UpdateDate
 * @property string $UpdateTerminal
 * @property string $SLA_REGISTER
 * @property string $SENAYAN_ID
 * @property string $NCIBookMan_ID
 * @property integer $CollectionMedia_id
 * @property string $MARC_LOC
 *
 * The followings are the available model relations:
 * @property Akuisisi[] $akuisisis
 * @property AkuisisiLog[] $akuisisiLogs
 * @property CatalogRuas[] $catalogRuases
 * @property Catalogfiles[] $catalogfiles
 * @property Catalogkardex[] $catalogkardexes
 * @property Branchs $branch
 * @property Worksheets $worksheet
 * @property Collectionmedias $collectionMedia
 * @property Collections[] $collections
 * @property Pengiriman[] $pengirimen
 */
class Catalogs extends CActiveRecord
{
	public $defaultColumns = array();

	/**
	 * Returns the static model of the specified AR class.
	 * Please note that you should have this exact method in all your CActiveRecord descendants!
	 * @param string $className active record class name.
	 * @return Catalogs the static model class
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
		return 'catalogs';
	}

	/**
	 * @return array validation rules for model attributes.
	 */
	public function rules()
	{
		// NOTE: you should only define rules for those attributes that
		// will receive user inputs.
		return array(
			array('IsOPAC, IsBNI, IsKIN, IsDelete, Branch_id, Worksheet_id, CollectionMedia_id', 'numerical', 'integerOnly'=>true),
			array('ControlNumber, BIBID, CoverURL, FileURL', 'length', 'max'=>255),
			array('CreateBy, CreateTerminal, UpdateBy, UpdateTerminal', 'length', 'max'=>100),
			array('SLA_REGISTER, SENAYAN_ID, NCIBookMan_ID', 'length', 'max'=>45),
			array('Title, Author, Edition, Publisher, PublishLocation, PublishYear, Paging, Ill, Sizes, Item, Subject, Description, ISBN, CallNumber, Note, Languages, DeweyNo, ApproveDateOPAC, MARC, CreateDate, UpdateDate, MARC_LOC', 'safe'),
			// The following rule is used by search().
			// @todo Please remove those attributes that should not be searched.
			array('ID, ControlNumber, BIBID, Title, Author, Edition, Publisher, PublishLocation, PublishYear, Paging, Ill, Sizes, Item, Subject, Description, ISBN, CallNumber, Note, Languages, DeweyNo, ApproveDateOPAC, IsOPAC, IsBNI, IsKIN, IsDelete, CoverURL, FileURL, MARC, Branch_id, Worksheet_id, CreateBy, CreateDate, CreateTerminal, UpdateBy, UpdateDate, UpdateTerminal, SLA_REGISTER, SENAYAN_ID, NCIBookMan_ID, CollectionMedia_id, MARC_LOC', 'safe', 'on'=>'search'),
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
			'akuisisis_relation' => array(self::HAS_MANY, 'Akuisisi', 'CatalogID'),
			'akuisisiLogs_relation' => array(self::HAS_MANY, 'AkuisisiLog', 'CatalogID'),
			'catalogRuases_relation' => array(self::HAS_MANY, 'CatalogRuas', 'CatalogId'),
			'catalogfiles_relation' => array(self::HAS_MANY, 'Catalogfiles', 'Catalog_id'),
			'catalogkardexes_relation' => array(self::HAS_MANY, 'Catalogkardex', 'Catalog_id'),
			'branch_relation' => array(self::BELONGS_TO, 'Branchs', 'Branch_id'),
			'worksheet_relation' => array(self::BELONGS_TO, 'Worksheets', 'Worksheet_id'),
			'collectionMedia_relation' => array(self::BELONGS_TO, 'Collectionmedias', 'CollectionMedia_id'),
			'collections_relation' => array(self::HAS_MANY, 'Collections', 'Catalog_id'),
			'pengirimen_relation' => array(self::HAS_MANY, 'Pengiriman', 'BibId'),
		);
	}

	/**
	 * @return array customized attribute labels (name=>label)
	 */
	public function attributeLabels()
	{
		return array(
			'ID' => Yii::t('attribute', 'ID'),
			'ControlNumber' => Yii::t('attribute', 'Control Number'),
			'BIBID' => Yii::t('attribute', 'Bibid'),
			'Title' => Yii::t('attribute', 'Title'),
			'Author' => Yii::t('attribute', 'Author'),
			'Edition' => Yii::t('attribute', 'Edition'),
			'Publisher' => Yii::t('attribute', 'Publisher'),
			'PublishLocation' => Yii::t('attribute', 'Publish Location'),
			'PublishYear' => Yii::t('attribute', 'Publish Year'),
			'Paging' => Yii::t('attribute', 'Paging'),
			'Ill' => Yii::t('attribute', 'Ill'),
			'Sizes' => Yii::t('attribute', 'Sizes'),
			'Item' => Yii::t('attribute', 'Item'),
			'Subject' => Yii::t('attribute', 'Subject'),
			'Description' => Yii::t('attribute', 'Description'),
			'ISBN' => Yii::t('attribute', 'Isbn'),
			'CallNumber' => Yii::t('attribute', 'Call Number'),
			'Note' => Yii::t('attribute', 'Note'),
			'Languages' => Yii::t('attribute', 'Languages'),
			'DeweyNo' => Yii::t('attribute', 'Dewey No'),
			'ApproveDateOPAC' => Yii::t('attribute', 'Approve Date Opac'),
			'IsOPAC' => Yii::t('attribute', 'Is Opac'),
			'IsBNI' => Yii::t('attribute', 'Is Bni'),
			'IsKIN' => Yii::t('attribute', 'Is Kin'),
			'IsDelete' => Yii::t('attribute', 'Is Delete'),
			'CoverURL' => Yii::t('attribute', 'Cover Url'),
			'FileURL' => Yii::t('attribute', 'File Url'),
			'MARC' => Yii::t('attribute', 'Marc'),
			'Branch_id' => Yii::t('attribute', 'Branch'),
			'Worksheet_id' => Yii::t('attribute', 'Worksheet'),
			'CreateBy' => Yii::t('attribute', 'Create By'),
			'CreateDate' => Yii::t('attribute', 'Create Date'),
			'CreateTerminal' => Yii::t('attribute', 'Create Terminal'),
			'UpdateBy' => Yii::t('attribute', 'Update By'),
			'UpdateDate' => Yii::t('attribute', 'Update Date'),
			'UpdateTerminal' => Yii::t('attribute', 'Update Terminal'),
			'SLA_REGISTER' => Yii::t('attribute', 'Sla Register'),
			'SENAYAN_ID' => Yii::t('attribute', 'Senayan'),
			'NCIBookMan_ID' => Yii::t('attribute', 'Ncibook Man'),
			'CollectionMedia_id' => Yii::t('attribute', 'Collection Media'),
			'MARC_LOC' => Yii::t('attribute', 'Marc Loc'),
		);
		/*
			'ID' => 'ID',
			'Control Number' => 'Control Number',
			'Bibid' => 'Bibid',
			'Title' => 'Title',
			'Author' => 'Author',
			'Edition' => 'Edition',
			'Publisher' => 'Publisher',
			'Publish Location' => 'Publish Location',
			'Publish Year' => 'Publish Year',
			'Paging' => 'Paging',
			'Ill' => 'Ill',
			'Sizes' => 'Sizes',
			'Item' => 'Item',
			'Subject' => 'Subject',
			'Description' => 'Description',
			'Isbn' => 'Isbn',
			'Call Number' => 'Call Number',
			'Note' => 'Note',
			'Languages' => 'Languages',
			'Dewey No' => 'Dewey No',
			'Approve Date Opac' => 'Approve Date Opac',
			'Is Opac' => 'Is Opac',
			'Is Bni' => 'Is Bni',
			'Is Kin' => 'Is Kin',
			'Is Delete' => 'Is Delete',
			'Cover Url' => 'Cover Url',
			'File Url' => 'File Url',
			'Marc' => 'Marc',
			'Branch' => 'Branch',
			'Worksheet' => 'Worksheet',
			'Create By' => 'Create By',
			'Create Date' => 'Create Date',
			'Create Terminal' => 'Create Terminal',
			'Update By' => 'Update By',
			'Update Date' => 'Update Date',
			'Update Terminal' => 'Update Terminal',
			'Sla Register' => 'Sla Register',
			'Senayan' => 'Senayan',
			'Ncibook Man' => 'Ncibook Man',
			'Collection Media' => 'Collection Media',
			'Marc Loc' => 'Marc Loc',
		
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
		$criteria->compare('t.ControlNumber',strtolower($this->ControlNumber),true);
		$criteria->compare('t.BIBID',strtolower($this->BIBID),true);
		$criteria->compare('t.Title',strtolower($this->Title),true);
		$criteria->compare('t.Author',strtolower($this->Author),true);
		$criteria->compare('t.Edition',strtolower($this->Edition),true);
		$criteria->compare('t.Publisher',strtolower($this->Publisher),true);
		$criteria->compare('t.PublishLocation',strtolower($this->PublishLocation),true);
		$criteria->compare('t.PublishYear',strtolower($this->PublishYear),true);
		$criteria->compare('t.Paging',strtolower($this->Paging),true);
		$criteria->compare('t.Ill',strtolower($this->Ill),true);
		$criteria->compare('t.Sizes',strtolower($this->Sizes),true);
		$criteria->compare('t.Item',strtolower($this->Item),true);
		$criteria->compare('t.Subject',strtolower($this->Subject),true);
		$criteria->compare('t.Description',strtolower($this->Description),true);
		$criteria->compare('t.ISBN',strtolower($this->ISBN),true);
		$criteria->compare('t.CallNumber',strtolower($this->CallNumber),true);
		$criteria->compare('t.Note',strtolower($this->Note),true);
		$criteria->compare('t.Languages',strtolower($this->Languages),true);
		$criteria->compare('t.DeweyNo',strtolower($this->DeweyNo),true);
		if($this->ApproveDateOPAC != null && !in_array($this->ApproveDateOPAC, array('0000-00-00 00:00:00', '0000-00-00')))
			$criteria->compare('date(t.ApproveDateOPAC)',date('Y-m-d', strtotime($this->ApproveDateOPAC)));
		$criteria->compare('t.IsOPAC',$this->IsOPAC);
		$criteria->compare('t.IsBNI',$this->IsBNI);
		$criteria->compare('t.IsKIN',$this->IsKIN);
		$criteria->compare('t.IsDelete',$this->IsDelete);
		$criteria->compare('t.CoverURL',strtolower($this->CoverURL),true);
		$criteria->compare('t.FileURL',strtolower($this->FileURL),true);
		$criteria->compare('t.MARC',strtolower($this->MARC),true);
		if(isset($_GET['Branch']))
			$criteria->compare('t.Branch_id',$_GET['Branch']);
		else
			$criteria->compare('t.Branch_id',$this->Branch_id);
		if(isset($_GET['Worksheet']))
			$criteria->compare('t.Worksheet_id',$_GET['Worksheet']);
		else
			$criteria->compare('t.Worksheet_id',$this->Worksheet_id);
		$criteria->compare('t.CreateBy',strtolower($this->CreateBy),true);
		if($this->CreateDate != null && !in_array($this->CreateDate, array('0000-00-00 00:00:00', '0000-00-00')))
			$criteria->compare('date(t.CreateDate)',date('Y-m-d', strtotime($this->CreateDate)));
		$criteria->compare('t.CreateTerminal',strtolower($this->CreateTerminal),true);
		$criteria->compare('t.UpdateBy',strtolower($this->UpdateBy),true);
		if($this->UpdateDate != null && !in_array($this->UpdateDate, array('0000-00-00 00:00:00', '0000-00-00')))
			$criteria->compare('date(t.UpdateDate)',date('Y-m-d', strtotime($this->UpdateDate)));
		$criteria->compare('t.UpdateTerminal',strtolower($this->UpdateTerminal),true);
		$criteria->compare('t.SLA_REGISTER',strtolower($this->SLA_REGISTER),true);
		$criteria->compare('t.SENAYAN_ID',strtolower($this->SENAYAN_ID),true);
		$criteria->compare('t.NCIBookMan_ID',strtolower($this->NCIBookMan_ID),true);
		if(isset($_GET['CollectionMedia']))
			$criteria->compare('t.CollectionMedia_id',$_GET['CollectionMedia']);
		else
			$criteria->compare('t.CollectionMedia_id',$this->CollectionMedia_id);
		$criteria->compare('t.MARC_LOC',strtolower($this->MARC_LOC),true);

		if(!isset($_GET['Catalogs_sort']))
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
			$this->defaultColumns[] = 'ControlNumber';
			$this->defaultColumns[] = 'BIBID';
			$this->defaultColumns[] = 'Title';
			$this->defaultColumns[] = 'Author';
			$this->defaultColumns[] = 'Edition';
			$this->defaultColumns[] = 'Publisher';
			$this->defaultColumns[] = 'PublishLocation';
			$this->defaultColumns[] = 'PublishYear';
			$this->defaultColumns[] = 'Paging';
			$this->defaultColumns[] = 'Ill';
			$this->defaultColumns[] = 'Sizes';
			$this->defaultColumns[] = 'Item';
			$this->defaultColumns[] = 'Subject';
			$this->defaultColumns[] = 'Description';
			$this->defaultColumns[] = 'ISBN';
			$this->defaultColumns[] = 'CallNumber';
			$this->defaultColumns[] = 'Note';
			$this->defaultColumns[] = 'Languages';
			$this->defaultColumns[] = 'DeweyNo';
			$this->defaultColumns[] = 'ApproveDateOPAC';
			$this->defaultColumns[] = 'IsOPAC';
			$this->defaultColumns[] = 'IsBNI';
			$this->defaultColumns[] = 'IsKIN';
			$this->defaultColumns[] = 'IsDelete';
			$this->defaultColumns[] = 'CoverURL';
			$this->defaultColumns[] = 'FileURL';
			$this->defaultColumns[] = 'MARC';
			$this->defaultColumns[] = 'Branch_id';
			$this->defaultColumns[] = 'Worksheet_id';
			$this->defaultColumns[] = 'CreateBy';
			$this->defaultColumns[] = 'CreateDate';
			$this->defaultColumns[] = 'CreateTerminal';
			$this->defaultColumns[] = 'UpdateBy';
			$this->defaultColumns[] = 'UpdateDate';
			$this->defaultColumns[] = 'UpdateTerminal';
			$this->defaultColumns[] = 'SLA_REGISTER';
			$this->defaultColumns[] = 'SENAYAN_ID';
			$this->defaultColumns[] = 'NCIBookMan_ID';
			$this->defaultColumns[] = 'CollectionMedia_id';
			$this->defaultColumns[] = 'MARC_LOC';
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
			$this->defaultColumns[] = 'ControlNumber';
			$this->defaultColumns[] = 'BIBID';
			$this->defaultColumns[] = 'Title';
			$this->defaultColumns[] = 'Author';
			$this->defaultColumns[] = 'Edition';
			$this->defaultColumns[] = 'Publisher';
			$this->defaultColumns[] = 'PublishLocation';
			$this->defaultColumns[] = 'PublishYear';
			$this->defaultColumns[] = 'Paging';
			$this->defaultColumns[] = 'Ill';
			$this->defaultColumns[] = 'Sizes';
			$this->defaultColumns[] = 'Item';
			$this->defaultColumns[] = 'Subject';
			$this->defaultColumns[] = 'Description';
			$this->defaultColumns[] = 'ISBN';
			$this->defaultColumns[] = 'CallNumber';
			$this->defaultColumns[] = 'Note';
			$this->defaultColumns[] = 'Languages';
			$this->defaultColumns[] = 'DeweyNo';
			$this->defaultColumns[] = array(
				'name' => 'ApproveDateOPAC',
				'value' => 'Utility::dateFormat($data->ApproveDateOPAC)',
				'htmlOptions' => array(
					'class' => 'center',
				),
				'filter' => Yii::app()->controller->widget('zii.widgets.jui.CJuiDatePicker', array(
					'model'=>$this,
					'attribute'=>'ApproveDateOPAC',
					'language' => 'ja',
					'i18nScriptFile' => 'jquery.ui.datepicker-en.js',
					//'mode'=>'datetime',
					'htmlOptions' => array(
						'id' => 'ApproveDateOPAC_filter',
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
					'name' => 'IsOPAC',
					'value' => 'Utility::getPublish(Yii::app()->controller->createUrl("IsOPAC",array("id"=>$data->ID)), $data->IsOPAC, 1)',
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
			if(!isset($_GET['type'])) {
				$this->defaultColumns[] = array(
					'name' => 'IsBNI',
					'value' => 'Utility::getPublish(Yii::app()->controller->createUrl("IsBNI",array("id"=>$data->ID)), $data->IsBNI, 1)',
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
			if(!isset($_GET['type'])) {
				$this->defaultColumns[] = array(
					'name' => 'IsKIN',
					'value' => 'Utility::getPublish(Yii::app()->controller->createUrl("IsKIN",array("id"=>$data->ID)), $data->IsKIN, 1)',
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
			$this->defaultColumns[] = 'CoverURL';
			$this->defaultColumns[] = 'FileURL';
			$this->defaultColumns[] = 'MARC';
			$this->defaultColumns[] = 'Branch_id';
			$this->defaultColumns[] = 'Worksheet_id';
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
			$this->defaultColumns[] = 'SLA_REGISTER';
			$this->defaultColumns[] = 'SENAYAN_ID';
			$this->defaultColumns[] = 'NCIBookMan_ID';
			$this->defaultColumns[] = 'CollectionMedia_id';
			$this->defaultColumns[] = 'MARC_LOC';
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
			//$this->ApproveDateOPAC = date('Y-m-d', strtotime($this->ApproveDateOPAC));
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