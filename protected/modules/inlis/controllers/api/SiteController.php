<?php
/**
 * SiteController
 * @var $this SiteController
 * @var $model SyncCatalogs
 * @var $form CActiveForm
 * version: 0.0.1
 * Reference start
 *
 * TOC :
 *	Index
 *	Search
 *	Advanced
 *	Detail
 *
 *	LoadModel
 *	performAjaxValidation
 *
 * @author Putra Sudaryanto <putra.sudaryanto@gmail.com>
 * @copyright Copyright (c) 2016 Ommu Platform (ommu.co)
 * @created date 30 March 2016, 01:49 WIB
 * @link http://company.ommu.co
 * @contect (+62)856-299-4114
 *
 *----------------------------------------------------------------------------------------------------------
 */

class SiteController extends Controller
{
	/**
	 * @var string the default layout for the views. Defaults to '//layouts/column2', meaning
	 * using two-column layout. See 'protected/views/layouts/column2.php'.
	 */
	//public $layout='//layouts/column2';
	public $defaultAction = 'index';

	/**
	 * @return array action filters
	 */
	public function filters() 
	{
		return array(
			'accessControl', // perform access control for CRUD operations
			//'postOnly + delete', // we only allow deletion via POST request
		);
	}

	/**
	 * Specifies the access control rules.
	 * This method is used by the 'accessControl' filter.
	 * @return array access control rules
	 */
	public function accessRules() 
	{
		return array(
			array('allow',  // allow all users to perform 'index' and 'view' actions
				'actions'=>array('index','search','advanced','detail'),
				'users'=>array('*'),
			),
			array('allow', // allow authenticated user to perform 'create' and 'update' actions
				'actions'=>array(),
				'users'=>array('@'),
				'expression'=>'isset(Yii::app()->user->level)',
				//'expression'=>'isset(Yii::app()->user->level) && (Yii::app()->user->level != 1)',
			),
			array('allow', // allow admin user to perform 'admin' and 'delete' actions
				'actions'=>array(),
				'users'=>array('admin'),
			),
			array('deny',  // deny all users
				'users'=>array('*'),
			),
		);
	}
	
	/**
	 * Lists all models.
	 */
	public function actionIndex() 
	{
		$this->redirect(Yii::app()->createUrl('site/index'));
	}
	
	/**
	 * Lists all models.
	 */
	public function actionSearch() 
	{
		if(Yii::app()->request->isPostRequest) {
			$criteria=new CDbCriteria;
			$keyword = trim($_POST['keyword']);
			$category = trim($_POST['category']);
			
			if($category == 'title')
				$criteria->compare('t.Title',strtolower($keyword),true);
			else if($category == 'author')
				$criteria->compare('t.Author',strtolower($keyword),true);
			else if($category == 'publisher')
				$criteria->compare('t.Publisher',strtolower($keyword),true);
			else if($category == 'publishyear')
				$criteria->compare('t.PublishYear',strtolower($keyword),true);
			else if($category == 'subject')
				$criteria->compare('t.Subject',strtolower($keyword),true);
			else if($category == 'callnumber')
				$criteria->compare('t.CallNumber',strtolower($keyword),true);
			else if($category == 'bibid')
				$criteria->compare('t.BIBID',strtolower($keyword),true);
			else if($category == 'isbn')
				$criteria->compare('t.ISBN',strtolower($keyword),true);
			$criteria->compare('t.IsOPAC',1);
			
			$dataProvider = new CActiveDataProvider('SyncCatalogs', array(
				'criteria'=>$criteria,
				'pagination'=>array(
					'pageSize'=>20,
				),
			));			
			$model = $dataProvider->getData();
			
			$data = '';
			if(!empty($model)) {
				foreach($model as $key => $item) {					
					$data[] = array(
						'id'=>$item->ID,
						'title'=>$item->Title,
						'author'=>$item->Author,
						'publisher'=>$item->Publisher,
						'publish_location'=>$item->PublishLocation,
						'publish_year'=>$item->PublishYear,
						'subject'=>$item->Subject,
						'isbn'=>$item->ISBN,
						'callnumber'=>$item->CallNumber,
					);					
				}
			} else
				$data = array();
		
			$pager = OFunction::getDataProviderPager($dataProvider);
			$get = array_merge($_GET, array($pager['pageVar']=>$pager['nextPage']));
			$nextPager = $pager['nextPage'] != 0 ? OFunction::validHostURL(Yii::app()->controller->createUrl('search', $get)) : '-';
			$return = array(
				'data' => $data,
				'pager' => $pager,
				'nextPager' => $nextPager,
			);
			echo CJSON::encode($return);
			
		} else 
			$this->redirect(Yii::app()->createUrl('site/index'));
	}
	
	/**
	 * Lists all models.
	 */
	public function actionAdvanced()
	{
		if(Yii::app()->request->isPostRequest) {
			$title = trim($_POST['title']);
			$author = trim($_POST['author']);
			$publisher = trim($_POST['publisher']);
			$publishyear = trim($_POST['publishyear']);
			$subject = trim($_POST['subject']);
			$callnumber = trim($_POST['callnumber']);
			$bibid = trim($_POST['bibid']);
			$isbn = trim($_POST['isbn']);
			
			$criteria=new CDbCriteria;
			$criteria->compare('t.Title',strtolower($title),true);
			$criteria->compare('t.Author',strtolower($author),true);
			$criteria->compare('t.Publisher',strtolower($publisher),true);
			$criteria->compare('t.PublishYear',strtolower($publishyear),true);
			$criteria->compare('t.Subject',strtolower($subject),true);
			$criteria->compare('t.CallNumber',strtolower($callnumber),true);
			$criteria->compare('t.BIBID',strtolower($bibid),true);
			$criteria->compare('t.ISBN',strtolower($isbn),true);
			$criteria->compare('t.IsOPAC',1);

			$dataProvider = new CActiveDataProvider('SyncCatalogs', array(
				'criteria'=>$criteria,
				'pagination'=>array(
					'pageSize'=>20,
				),
			));			
			$model = $dataProvider->getData();
			
			$data = '';
			if(!empty($model)) {
				foreach($model as $key => $item) {					
					$data[] = array(
						'id'=>$item->ID,
						'title'=>$item->Title,
						'author'=>$item->Author,
						'publisher'=>$item->Publisher,
						'publish_location'=>$item->PublishLocation,
						'publish_year'=>$item->PublishYear,
						'subject'=>$item->Subject,
						'isbn'=>$item->ISBN,
						'callnumber'=>$item->CallNumber,
					);					
				}
			} else
				$data = array();
		
			$pager = OFunction::getDataProviderPager($dataProvider);
			$get = array_merge($_GET, array($pager['pageVar']=>$pager['nextPage']));
			$nextPager = $pager['nextPage'] != 0 ? OFunction::validHostURL(Yii::app()->controller->createUrl('advanced', $get)) : '-';
			$return = array(
				'data' => $data,
				'pager' => $pager,
				'nextPager' => $nextPager,
			);
			echo CJSON::encode($return);
			
		} else 
			$this->redirect(Yii::app()->createUrl('site/index'));
	}
	
	/**
	 * Lists all models.
	 */
	public function actionDetail()
	{
		if(Yii::app()->request->isPostRequest) {
			$id = trim($_POST['id']);
			
			$model=$this->loadModel($id);
			
			if(!empty($model)) {
				$return = array(
					'success'=>'1',
					'id'=>$model->ID,
					'title'=>$model->Title,
					'author'=>$model->Author,
					'publisher'=>$model->Publisher,
					'publish_location'=>$model->PublishLocation,
					'publish_year'=>$model->PublishYear,
					'subject'=>$model->Subject,
					'isbn'=>$model->ISBN,
					'callnumber'=>$model->CallNumber,
					'worksheet'=>$model->worksheet->Name,
				);
			} else {
				$return = array(
					'success'=>'0',
				);
			}				
			echo CJSON::encode($return);
			
		} else 
			$this->redirect(Yii::app()->createUrl('site/index'));
	}

	/**
	 * Returns the data model based on the primary key given in the GET variable.
	 * If the data model is not found, an HTTP exception will be raised.
	 * @param integer the ID of the model to be loaded
	 */
	public function loadModel($id) 
	{
		$model = SyncCatalogs::model()->findByPk($id);
		if($model===null)
			throw new CHttpException(404, Yii::t('phrase', 'The requested page does not exist.'));
		return $model;
	}

	/**
	 * Performs the AJAX validation.
	 * @param CModel the model to be validated
	 */
	protected function performAjaxValidation($model) 
	{
		if(isset($_POST['ajax']) && $_POST['ajax']==='sync-catalogs-form') {
			echo CActiveForm::validate($model);
			Yii::app()->end();
		}
	}
}
