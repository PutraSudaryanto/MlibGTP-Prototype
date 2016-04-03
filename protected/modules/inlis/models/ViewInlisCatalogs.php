<?php
/**
 * ViewInlisCatalogs
 * version: 0.0.1
 *
 * @author Putra Sudaryanto <putra.sudaryanto@gmail.com>
 * @copyright Copyright (c) 2016 Ommu Platform (ommu.co)
 * @created date 1 April 2016, 09:14 WIB
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
 * This is the model class for table "_view_inlis_catalogs".
 *
 * The followings are the available columns in table '_view_inlis_catalogs':
 * @property string $catalog_id
 * @property string $bookmarks
 * @property string $favourites
 * @property string $likes
 * @property string $views
 * @property string $user_view
 */
class ViewInlisCatalogs extends CActiveRecord
{
	public $defaultColumns = array();

	/**
	 * Returns the static model of the specified AR class.
	 * Please note that you should have this exact method in all your CActiveRecord descendants!
	 * @param string $className active record class name.
	 * @return ViewInlisCatalogs the static model class
	 */
	public static function model($className=__CLASS__)
	{
		return parent::model($className);
	}

	/**
	 * @return string the associated database table name
	 */
	public function tableName()
	{
		return '_view_inlis_catalogs';
	}

	/**
	 * @return string the primarykey column
	 */
	public function primaryKey()
	{
		return 'catalog_id';
	}

	/**
	 * @return array validation rules for model attributes.
	 */
	public function rules()
	{
		// NOTE: you should only define rules for those attributes that
		// will receive user inputs.
		return array(
			array('catalog_id', 'required'),
			array('catalog_id', 'length', 'max'=>11),
			array('bookmarks, favourites, likes, views, user_view', 'length', 'max'=>21),
			// The following rule is used by search().
			// @todo Please remove those attributes that should not be searched.
			array('catalog_id, bookmarks, favourites, likes, views, user_view', 'safe', 'on'=>'search'),
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
		);
	}

	/**
	 * @return array customized attribute labels (name=>label)
	 */
	public function attributeLabels()
	{
		return array(
			'catalog_id' => Yii::t('attribute', 'Catalog'),
			'bookmarks' => Yii::t('attribute', 'Bookmarks'),
			'favourites' => Yii::t('attribute', 'Favourites'),
			'likes' => Yii::t('attribute', 'Likes'),
			'views' => Yii::t('attribute', 'Views'),
			'user_view' => Yii::t('attribute', 'User View'),
		);
		/*
			'Catalog' => 'Catalog',
			'Bookmarks' => 'Bookmarks',
			'Favourites' => 'Favourites',
			'Likes' => 'Likes',
			'Views' => 'Views',
			'User View' => 'User View',
		
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
		
		$criteria->compare('t.catalog_id',strtolower($this->catalog_id),true);
		$criteria->compare('t.bookmarks',strtolower($this->bookmarks),true);
		$criteria->compare('t.favourites',strtolower($this->favourites),true);
		$criteria->compare('t.likes',strtolower($this->likes),true);
		$criteria->compare('t.views',strtolower($this->views),true);
		$criteria->compare('t.user_view',strtolower($this->user_view),true);

		if(!isset($_GET['ViewInlisCatalogs_sort']))
			$criteria->order = 't.catalog_id DESC';

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
			$this->defaultColumns[] = 'catalog_id';
			$this->defaultColumns[] = 'bookmarks';
			$this->defaultColumns[] = 'favourites';
			$this->defaultColumns[] = 'likes';
			$this->defaultColumns[] = 'views';
			$this->defaultColumns[] = 'user_view';
		}

		return $this->defaultColumns;
	}

	/**
	 * Set default columns to display
	 */
	protected function afterConstruct() {
		if(count($this->defaultColumns) == 0) {
			$this->defaultColumns[] = array(
				'header' => 'No',
				'value' => '$this->grid->dataProvider->pagination->currentPage*$this->grid->dataProvider->pagination->pageSize + $row+1'
			);
			$this->defaultColumns[] = 'catalog_id';
			$this->defaultColumns[] = 'bookmarks';
			$this->defaultColumns[] = 'favourites';
			$this->defaultColumns[] = 'likes';
			$this->defaultColumns[] = 'views';
			$this->defaultColumns[] = 'user_view';
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

}