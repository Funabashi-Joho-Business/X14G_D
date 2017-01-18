//スプレッドシートをIDをキャッシュしつつ開く
//スラッシュ区切りでフォルダを自動生成
function openSheetFile(name){
  //スプレッドシートのIDを検索
  var id = PropertiesService.getScriptProperties().getProperty("FILE_ID:"+name);
  //IDがあればスプレッドシートを開く
  if(id != null)
  {
    try{
      var file;
        file = SpreadsheetApp.openById(id);
      if(file)
        return file;
      }catch(e){}
  }
  //既存のシートか新規シートの取得
  file = getSheetFile(name,true);
  if(!file)
    return null;
  //IDを保存
  PropertiesService.getScriptProperties().setProperty("FILE_ID:"+name,file.getId());
  return file;
}

//スプレッドシートを開く(新規作成込み)
function getSheetFile(name,flag) {
  //フォルダ名を分解
  var bar = name.split("/");
  //フォルダの階層を辿る
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
  //ファイルの取得
  var file = null;
  if(folder.getFilesByName(bar[i]).hasNext())
    file = folder.getFilesByName(bar[i]).next();
  if(!file){
    //ファイルが見つからなかったらスプレッドシートの作成
    file = SpreadsheetApp.create(bar[i]);
    if(!file)
      return null;
    //スプレッドシートをDrive操作用インスタンスに変換
    var file2 = DriveApp.getFileById(file.getId());
    if(folder !=  DriveApp){
      //フォルダ移動
      folder.addFile(file2);
      DriveApp.removeFile(file2);
    }
    return file; 
  }
  return SpreadsheetApp.openById(file.getId()); 
}