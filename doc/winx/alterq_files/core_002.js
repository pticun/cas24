define.deployment("core.deployment.main",function(a){a.atPhase(PHASES.MANAGERS,function(a){a.publishBootstrapClass("_wixify_","core.utils.Wixify"),a.createClassInstance("W.LinkTypes","core.managers.LinkTypesManager"),a.createClassInstance("W.Data","core.managers.data.DataManager"),a.createClassInstance("W.ComponentData","core.managers.data.ComponentDataManager"),a.createClassInstance("W.Components","core.managers.component.ComponentManager"),a.createClassInstance("W.Config","core.managers.ConfigurationManager"),a.createClassInstance("W.Resources","core.managers.ResourceManager"),a.createClassInstance("W.Css","core.managers.css.CssManager"),a.createClassInstance("W.Theme","core.managers.theme.ThemeManager"),a.createClassInstance("W.Skins","core.managers.skin.SkinManager"),a.createClassInstance("W.ComponentLifecycle","core.managers.componentlifecyclemanager.ComponentLifecycleManager")})});
/*
//@ sourceMappingURL=core.map.js
*/