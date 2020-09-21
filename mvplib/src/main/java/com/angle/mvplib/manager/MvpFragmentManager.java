package com.angle.mvplib.manager;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.angle.mvplib.base.view.BaseFragment;

public class MvpFragmentManager {
    /**
     *
     * @param manager fragment管理器
     * @param willFragment 要显示是的fragmet
     * @param perFragment  将要隐藏的Fragment
     * @param containerId  容器的id
     * @return
     *  1.得到该Fragment的tag
     *  如果没有找到该tag说明该Fragment没有实例化/实例化该Fragmet、是否加入回退栈
     *  如果在tag中找到该实例/判断是否加入回退栈 如果回退栈里没有记录对操作
     *  对上一个Frament进行添加
     */
    public static BaseFragment hidOrShowFramgent(FragmentManager manager, Class<? extends BaseFragment> willFragment, BaseFragment perFragment, @IdRes int containerId) {
        FragmentTransaction transaction = manager.beginTransaction();
        String tag = willFragment.getName();
        BaseFragment willShowFragment = (BaseFragment) manager.findFragmentByTag(tag);
//        如果Fragemtn没有被添加
        try {
            if (willShowFragment == null) {

                willShowFragment = willFragment.newInstance();

                transaction.setCustomAnimations(willShowFragment.AnimationEnter(),
                        perFragment == null ? 0 : perFragment.AnimationExit(),
                        perFragment == null ? 0 : perFragment.AnimationPopEnter(),
                        willShowFragment.AnimationPopExit());

                transaction.add(containerId, willShowFragment, tag);
//            判断是否要将该Framgent添加到回退栈
                if (willShowFragment.isNeedAddToBackStacl()) {
                    transaction.addToBackStack(tag);//将该事物添加到回退栈
                }
//            如果以及存在
            } else {
//            得到Framgment栈中的数量
                int backStackEntryCount = manager.getBackStackEntryCount();
                FragmentManager.BackStackEntry backStackEntryAt = null;
                for (int i = 0; i < backStackEntryCount; i++) {
                    backStackEntryAt = manager.getBackStackEntryAt(i);
                    if (backStackEntryAt.getName().equals(tag)) {
//                    如果栈中有该Frament利用popBackStackImmediate利用Frament弹出该Frament之前所有的栈记录
                        manager.popBackStackImmediate(tag, 0);
                        return willShowFragment;
                    }
                }
//        如果栈中有事件却没有该事件弹出所有的事物返回第一个事物
                if (backStackEntryCount > 0) {
                    manager.popBackStackImmediate(manager.getBackStackEntryAt(0).getName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return willShowFragment;
                } else {
                    if (!willShowFragment.isAdded()) {
                        transaction.add(containerId, willShowFragment, tag);
                    }
                    if (willShowFragment.isDetached()) {
                        transaction.attach(willShowFragment);
                    }
                    if (willShowFragment.isHidden()) {
                        transaction.show(willShowFragment);
                    }
                }
            }
            handPerFragment(transaction, perFragment, willShowFragment);
            transaction.commit();
            return willShowFragment;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void handPerFragment(FragmentTransaction transaction, BaseFragment perFragment, BaseFragment willShowFragment) {
        if (perFragment != null) {
            switch (willShowFragment.handPerFragment()) {
                case HIDE:
                    transaction.hide(perFragment);
                    break;
                case REMOVE:
                    transaction.remove(perFragment);
                    break;
                case DETACHED:
                    transaction.detach(perFragment);
                    break;
            }
        }
    }
}
