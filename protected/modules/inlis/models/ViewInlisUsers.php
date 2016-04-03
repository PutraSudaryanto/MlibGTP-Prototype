<?php
/**
 * ViewInlisUsers
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
 * This is the model class for table "_view_inlis_users".
 *
 * The followings are the available columns in table '_view_inlis_users':
 * @property string $user_id
 * @property string $bookmarks
 * @property string $bookmark_all
 * @property string $favourites
 * @property string $favourite_all
 * @property string $likes
 * @property string $like_all
 * @property string $views
 * @property string $view_all
 * @property string $catalog_view
 */
class ViewInlisUsers extends CActiveRecord
{
	public $defaultColumns = array();

	/**
	 * Returns the static model of the specified AR class.
	 * Please note that you should have this exact method in all your CActiveRecord descendants!
	 * @param string $className active record class name.
	 * @return ViewInlisUsers the static model class
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
		return '_view_inlis_users';
	}

	/**
	 * @return string the primarykey column
	 */
	public function primaryKey()
	{
		return 'user_id';
	}

	/**
	 * @return array validation rules for model attributes.
	 */
	public function rules()
	{
		// NOTE: you should only define rules for those attributes that
		// will receive user inputs.
		return array(
			array('user_id', 'length', 'max'=>11),
			array('bookmark_all, favourites, favourite_all, likes, like_all, views, view_all, catalog_view', 'length', 'max'=>21),
			// The following rule is used by search().
			// @todo Please remove those attributes that should not be searched.
			array('user_id, bookmark_all, favourites, favourite_all, likes, like_all, views, view_all, catalog_view', 'safe', 'on'=>'search'),
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
			'user_id' => Yii::t('attribute', 'User'),
			'bookmarks' => Yii::t('attribute', 'Bookmarks'),
			'bookmark_all' => Yii::t('attribute', 'Bookmark All'),	
			'favourites' => Yii::t('attribute', 'Favourites'),
			'favourite_all' => Yii::t('attribute', 'Favourite All'),
			'likes' => Yii::t('attribute', 'Likes'),
			'like_all' => Yii::t('attribute', 'Like All'),
			'views' => Yii::t('attribute', 'Views'),
			'view_all' => Yii::t('attribute', 'View All'),
			'catalog_view' => Yii::t('attribute', 'Catalog View'),
		);
		/*
			'User' => 'User',
			'Bookmarks' => 'Bookmarks',
			'Bookmark All' => 'Bookmark All',
			'Favourites' => 'Favourites',
			'Favourite All' => 'Favourite All',
			'Likes' => 'Likes',
			'Like All' => 'Like All',
			'Views' => 'Views',
			'View All' => 'View All',
			'Catalog View' => 'Catalog View',
		
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

		$criteria->compare('t.user_id',$this->user_id);
		$criteria->compare('t.bookmarks',strtolower($this->bookmarks),true);
		$criteria->compare('t.bookmark_all',strtolower($this->bookmark_all),true);
		$criteria->compare('t.favourites',strtolower($this->favourites),true);
		$criteria->compare('t.favourite_all',strtolower($this->favourite_all),true);
		$criteria->compare('t.likes',strtolower($this->likes),true);
		$criteria->compare('t.like_all',strtolower($this->like_all),true);
		$criteria->compare('t.views',strtolower($this->views),true);
		$criteria->compare('t.view_all',strtolower($this->view_all),true);
		$criteria->compare('t.catalog_view',strtolower($this->catalog_view),true);

		if(!isset($_GET['ViewInlisUsers_sort']))
			$criteria->order = 't.user_id DESC';

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
			$this->defaultColumns[] = 'user_id';
			$this->defaultColumns[] = 'bookmarks';
			$this->defaultColumns[] = 'bookmark_all';
			$this->defaultColumns[] = 'favourites';
			$this->defaultColumns[] = 'favourite_all';
			$this->defaultColumns[] = 'likes';
			$this->defaultColumns[] = 'like_all';
			$this->defaultColumns[] = 'views';
			$this->defaultColumns[] = 'view_all';
			$this->defaultColumns[] = 'catalog_view';
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
			$this->defaultColumns[] = 'user_id';
			$this->defaultColumns[] = 'bookmarks';
			$this->defaultColumns[] = 'bookmark_all';
			$this->defaultColumns[] = 'favourites';
			$this->defaultColumns[] = 'favourite_all';
			$this->defaultColumns[] = 'likes';
			$this->defaultColumns[] = 'like_all';
			$this->defaultColumns[] = 'views';
			$this->defaultColumns[] = 'view_all';
			$this->defaultColumns[] = 'catalog_view';
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