//�X�v���b�h�V�[�g��ID���L���b�V�����J��
//�X���b�V����؂�Ńt�H���_����������
function openSheetFile(name){
  //�X�v���b�h�V�[�g��ID������
  var id = PropertiesService.getScriptProperties().getProperty("FILE_ID:"+name);
  //ID������΃X�v���b�h�V�[�g���J��
  if(id != null)
  {
    try{
      var file;
        file = SpreadsheetApp.openById(id);
      if(file)
        return file;
      }catch(e){}
  }
  //�����̃V�[�g���V�K�V�[�g�̎擾
  file = getSheetFile(name,true);
  if(!file)
    return null;
  //ID��ۑ�
  PropertiesService.getScriptProperties().setProperty("FILE_ID:"+name,file.getId());
  return file;
}

//�X�v���b�h�V�[�g���J��(�V�K�쐬����)
function getSheetFile(name,flag) {
  //�t�H���_���𕪉�
  var bar = name.split("/");
  //�t�H���_�̊K�w��H��
  var folder =  DriveApp;
  for(var i=0;i < bar.length-1;i++){
    var name = bar[i];
    if(name){
      var it = folder.getFoldersByName(name);
      if(!it.hasNext()){
        if(flag)
          folder = folder.createFolder(name);
        else
          folder = null;
        if(!folder)
           break;
      }
      else
        folder = it.next();
    } 
  }
  //�t�@�C���̎擾
  var file = null;
  if(folder.getFilesByName(bar[i]).hasNext())
    file = folder.getFilesByName(bar[i]).next();
  if(!file){
    //�t�@�C����������Ȃ�������X�v���b�h�V�[�g�̍쐬
    file = SpreadsheetApp.create(bar[i]);
    if(!file)
      return null;
    //�X�v���b�h�V�[�g��Drive����p�C���X�^���X�ɕϊ�
    var file2 = DriveApp.getFileById(file.getId());
    if(folder !=  DriveApp){
      //�t�H���_�ړ�
      folder.addFile(file2);
      DriveApp.removeFile(file2);
    }
    return file; 
  }
  return SpreadsheetApp.openById(file.getId()); 
}